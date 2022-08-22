package com.example.demo.utils.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName Head
 * @Description
 * @Date 2022/3/7 13:30
 * @Author mxn
 * @Version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="head")
@Data
public class Head {

    @XmlElement(name = "app_key")      // 子节点名称
    private String appKey;
    @XmlElement(name = "time_stamp")    // 子节点名称
    private String timeStamp;
    @XmlElement(name = "nonce_str")      // 子节点名称
    private String nonceStr;
    @XmlElement(name = "sign")     // 子节点名称
    private String sign;


}
