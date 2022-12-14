package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description
 * @Date 2022/8/22 17:25
 * @Author mxn
 * @Version 1.0
 */
@RestController
public class TestController {

    @Value("${config.name}")
    private String name;

    @RequestMapping("/index")
    public String index(String smg){
        return smg;
    }
    @RequestMapping("/index2")
    public String index(){
        return name;
    }
}
