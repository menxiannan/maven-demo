package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.utils.xml.CloudFengUtil;
import com.example.demo.utils.xml.XmlRemoteDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName XmlController
 * @Description  接收xml请求 demo
 * @Date 2022/3/7 13:44
 * @Author mxn
 * @Version 1.0
 */
@Slf4j
@RestController
public class XmlController {

    @PostMapping(value = "/xml")
    public String xmlPost(@RequestBody String requestBody) {
        XmlRemoteDto dto = (XmlRemoteDto) CloudFengUtil.convertXmlStrToObject(XmlRemoteDto.class, requestBody);
        return JSONObject.toJSONString(dto);

    }
}
