package com.example.demo.domain;

import net.dongliu.apk.parser.ApkFile;

import java.io.Serializable;

/**
 * @ClassName ApkInfo
 * @Description
 * @Date 2021/05/14 10:43
 * @Author mxn
 * @Version 1.0
 */

public class ApkInfo implements Serializable {

    private String versionName;
    private Long versionCode;
    private String packageName;
    //远程路径 服务器 路径+文件名
    private String iconPath;
    //本包路径
    private String iconLocalPath;
    private ApkFile apkFile;
    private String sign;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Long getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Long versionCode) {
        this.versionCode = versionCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getIconLocalPath() {
        return iconLocalPath;
    }

    public void setIconLocalPath(String iconLocalPath) {
        this.iconLocalPath = iconLocalPath;
    }

    public ApkFile getApkFile() {
        return apkFile;
    }

    public void setApkFile(ApkFile apkFile) {
        this.apkFile = apkFile;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
