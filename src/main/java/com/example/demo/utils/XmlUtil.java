package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @ClassName XmlUtil
 * @Description
 * @Date 2022/3/4 13:34
 * @Author mxn
 * @Version 1.0
 */

public class XmlUtil {
    public static void main(String[] args)throws Exception {
        Map<String, String> param = new TreeMap<>();
        param.put("name", "张三");
        param.put("age", "18");
        param.put("sex", "男");
        String xml = createXml(param);
        System.out.println("xml = \n" + xml);

    }
    public static String createXml(Map<String, String> param) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  // 初始化一个XML解析工厂
        // 创建一个DocumentBuilder实例
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 构建一个Document实例
        Document doc = builder.newDocument();
        doc.setXmlStandalone(true);
        // 把构造的XML结构，写入到具体的文件中
        TransformerFactory formerFactory = TransformerFactory.newInstance();
        Transformer transformer = formerFactory.newTransformer();
        // 换行 输出内容是否使用换行
        transformer.setOutputProperty(OutputKeys.INDENT, "YES");
        //首行根节点换行
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "");
        // 文档字符编码
        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        //设置缩进长度2
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        //创建根节点
        Element element = doc.createElement("msg:outboundMessageRequest");
        element.setAttribute("xmlns:msg", "urn:oma:xml:rest:netapi:messaging:1");
        //填充数据
        Set<Map.Entry<String, String>> entries = param.entrySet();
        Element childElement;
        for (Map.Entry<String, String> entry : entries) {
            childElement = doc.createElement(entry.getKey());
            childElement.setTextContent(entry.getValue());
            element.appendChild(childElement);
        }
        doc.appendChild(element);
        //将document中的信息转换为字符串输出到控制台中
        StringWriter stringWriter = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(stringWriter));
        return stringWriter.toString();
    }


    public static void mains() throws Exception {
        // 初始化一个XML解析工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 创建一个DocumentBuilder实例
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 构建一个Document实例
        Document doc = builder.newDocument();
        doc.setXmlStandalone(true);
        // standalone用来表示该文件是否呼叫其它外部的文件。若值是 ”yes” 表示没有呼叫外部文件
        // 创建一个根节点
        // 说明: doc.createElement("元素名")
        // element.setAttribute("属性名","属性值")
        // element.setTextContent("标签间内容")
        // element.appendChild("子节点element");
        // doc.appendChild("创建的父节点添加到Document实例")

        Element element = doc.createElement("msg:outboundMessageRequest");
        element.setAttribute("xmlns:msg", "urn:oma:xml:rest:netapi:messaging:1");


        Element destinationAddressElement = doc.createElement("destinationAddress");
        destinationAddressElement.setTextContent("tel:+8615669910310");
        element.appendChild(destinationAddressElement);

        Element contentType = doc.createElement("contentType");
        contentType.setTextContent("你好我是内容");
        element.appendChild(contentType);

        Element bodyText = doc.createElement("bodyText");
        bodyText.setTextContent("你好我是body");
        element.appendChild(bodyText);

        Element contributionID = doc.createElement("contributionID");
        contributionID.setTextContent("你好我是contributionID");
        element.appendChild(contributionID);
        doc.appendChild(element);
        // 把构造的XML结构，写入到具体的文件中
        TransformerFactory formerFactory = TransformerFactory.newInstance();
        Transformer transformer = formerFactory.newTransformer();
        // 换行 输出内容是否使用换行
        transformer.setOutputProperty(OutputKeys.INDENT, "YES");
        //首行根节点换行
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "");
        // 文档字符编码
        transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        //设置缩进长度16
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        // 可随意指定文件的后缀,效果一样,但xml比较好解析,比如: E:\\person.txt等
//        transformer.transform(new DOMSource(doc),new StreamResult(new File("D:\\person.xml")));


        //将document中的信息转换为字符串输出到控制台中
        StringWriter stringWriter = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(stringWriter));
        System.out.println(stringWriter.toString());
    }

}
