package com.example.demo.utils.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @ClassName Record
 * @Description
 * @Date 2022/3/7 13:33
 * @Author mxn
 * @Version 1.0
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="record")
@Data
public class Record {


    @XmlElement(name = "mission_num")
    private String missionNum;
    @XmlElement(name = "dest_id")
    private String destId;

    @XmlElement(name = "send_status")
    private String sendStatus;
    @XmlElement(name = "receive_status")
    private String receiveStatus;

    @XmlElement(name = "batch_num")
    private String batchNum;
    @XmlElement(name = "stat_time")
    private String statTime;
    // setter  getter 方法
}
