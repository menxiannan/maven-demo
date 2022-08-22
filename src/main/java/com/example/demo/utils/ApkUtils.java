package com.example.demo.utils;

import com.example.demo.domain.ApkInfo;
import jodd.util.StringUtil;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @ClassName ApkUtils
 * @Description
 * @Date 2020/8/7 9:27
 * @Author mxn
 * @Version 1.0
 */

public class ApkUtils {

    public static final Logger logger = LoggerFactory.getLogger(ApkUtils.class);

    /**
     * 解析apk
     *
     * @param path     路径
     * @param iconPath 保存icon到指定目录
     * @param iconName icon名称 带后缀
     * @return
     */
    public static ApkInfo extractApk(String path, String iconPath, String iconName) {
        if (StringUtil.isEmpty(path)) {
            return null;
        }
        return extractApk(new File(path), true, iconPath, iconName);
    }

    /**
     * 解析apk
     *
     * @param path 路径
     * @return
     */
    public static ApkInfo extractApk(String path) throws Exception {
        if (StringUtil.isEmpty(path)) {
            return null;
        }
        return extractApk(new File(path), false, null, null);
    }

    /**
     * 解析apk
     *
     * @param file     apk
     * @param iconPath 保存icon到指定目录
     * @param iconName icon名称 带后缀
     * @return
     */
    public static ApkInfo extractApk(File file, String iconPath, String iconName) {
        if (null == file || !file.exists()) {
            return null;
        }
        return extractApk(file, true, iconPath, iconName);
    }

    /**
     * 解析apk
     *
     * @param file apk
     * @return
     */
    public static ApkInfo extractApk(File file) {
        if (null == file || !file.exists()) {
            return null;
        }
        return extractApk(file, false, null, null);
    }

    /**
     * 解析apk 统一方法
     *
     * @param file         apk 文件
     * @param isUploadIcon 是否保存icon到指定目录
     * @param iconPath     icon目录
     * @param iconName     icon名称 带后缀
     * @return
     */
    public static ApkInfo extractApk(File file, boolean isUploadIcon, String iconPath, String iconName) {

        try {
            if (null == file || !file.exists()) {
                return null;
            }
            ApkInfo info = new ApkInfo();

            ApkFile apkFile = new ApkFile(file);

            ApkMeta apkMeta = apkFile.getApkMeta();

            info.setApkFile(apkFile);
            info.setVersionName(apkMeta.getVersionName());
            info.setVersionCode(apkMeta.getVersionCode());
            info.setPackageName(apkMeta.getPackageName());
            info.setIconLocalPath(apkMeta.getIcon());
            try {
                info.setSign(getSign(file));
            } catch (Exception e) {
                logger.error("ApkUtils.extractApk 获取签名失败 error:{}", e);
            }

            if (isUploadIcon) {
                try {
                    if (StringUtil.isEmpty(iconPath)) {
                        return null;
                    }
                    File i = new File(iconPath);
                    if (!i.exists()) {
                        i.mkdirs();
                    }
                    extractFileFromApk(file, apkMeta.getIcon(), iconPath + "/" + iconName);
                    info.setIconPath(iconPath + "/" + iconName);
                } catch (Exception e) {
                    logger.error("ApkUtils.extractApk 保存指定目录失败 error:{}", e);
                    return null;
                }
            }
            return info;
        } catch (Exception e) {
            logger.error("ApkUtils.extractApk error:{}", e);
        }
        return null;
    }

    /**
     * 获取apk签名
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static String getSign(String path) throws IOException {

        if (StringUtil.isEmpty(path)) {
            return null;
        }
        byte[] bytes = getSignaturesFromApk(new File(path));
        return hexDigest(bytes);
    }

    /**
     * 获取apk签名
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String getSign(File file) throws IOException {
        if (null == file || !file.exists()) {
            return null;
        }
        byte[] bytes = getSignaturesFromApk(file);
        return hexDigest(bytes);
    }

    /**
     * 从APK中读取签名 统一方法
     *
     * @return
     * @throws IOException
     */
    private static byte[] getSignaturesFromApk(File file) throws IOException {
        JarFile jarFile = new JarFile(file);
        try {
            JarEntry je = jarFile.getJarEntry("AndroidManifest.xml");
            byte[] readBuffer = new byte[8192];
            Certificate[] certs = loadCertificates(jarFile, je, readBuffer);
            if (certs != null) {
                for (Certificate c : certs) {
                    return c.getEncoded();
                }
            }
        } catch (Exception ex) {
        }
        return null;
    }

    /**
     * 处理
     *
     * @param bytes
     * @return
     */
    public static String hexDigest(byte[] bytes) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        byte[] md5Bytes = md5.digest(bytes);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    /**
     * 加载签名
     *
     * @param jarFile
     * @param je
     * @param readBuffer
     * @return
     */
    private static Certificate[] loadCertificates(JarFile jarFile, JarEntry je, byte[] readBuffer) {
        try {
            InputStream is = jarFile.getInputStream(je);
            while (is.read(readBuffer, 0, readBuffer.length) != -1) {
            }
            is.close();
            return je != null ? je.getCertificates() : null;
        } catch (IOException e) {
        }
        return null;
    }


    /**
     * 功能描述: 保存icon到指定目录
     *
     * @param file2      apk文件
     * @param fileName   icon 名称
     * @param outputPath 保存地址
     * @return void
     * @author 12084
     * @date 2020/8/15  16:37
     */
    public static void extractFileFromApk(File file2, String fileName, String outputPath) throws Exception {
        InputStream is = extractFileFromApk(file2, fileName);
        File file = new File(outputPath);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file), 1024);
        byte[] b = new byte[1024];
        BufferedInputStream bis = new BufferedInputStream(is, 1024);
        while (bis.read(b) != -1) {
            bos.write(b);
        }
        bos.flush();
        is.close();
        bis.close();
        bos.close();
    }

    public static InputStream extractFileFromApk(File file, String fileName) {
        try {
            ZipFile zFile = new ZipFile(file);
            ZipEntry entry = zFile.getEntry(fileName);
            entry.getComment();
            entry.getCompressedSize();
            entry.getCrc();
            entry.isDirectory();
            entry.getSize();
            entry.getMethod();
            InputStream stream = zFile.getInputStream(entry);
            return stream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
