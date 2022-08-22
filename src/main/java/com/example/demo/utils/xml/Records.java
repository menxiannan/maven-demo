package com.example.demo.utils.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @ClassName Records
 * @Description
 * @Date 2022/3/7 13:32
 * @Author mxn
 * @Version 1.0
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"record"})
@XmlRootElement(name="records")
@Data
public class Records {

    private List<Record> record;

}
