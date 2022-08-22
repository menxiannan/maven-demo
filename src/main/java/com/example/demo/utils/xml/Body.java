package com.example.demo.utils.xml;

import lombok.Data;

import javax.xml.bind.annotation.*;

/**
 * @ClassName Body
 * @Description
 * @Date 2022/3/7 13:32
 * @Author mxn
 * @Version 1.0
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"records"})	// 子节点
@XmlRootElement(name="body")   // 当前节点
@Data
public class Body {

    @XmlElement(name = "records")    // 子节点名称
    private Records records;

}
