package com.example.demo.utils.xml;

/**
 * @ClassName XmlRemoteDto
 * @Description
 * @Date 2022/3/7 13:28
 * @Author mxn
 * @Version 1.0
 */

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 最重要的就是 不要 XmlType  和 XmlRootElement 注解不要弄混了
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder ={"head","body"} )
@XmlRootElement(name = "xml")
@Data
public class XmlRemoteDto {

    private Head head;
    private Body body;
}
