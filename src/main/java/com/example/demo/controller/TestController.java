package com.example.demo.controller;

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

    @RequestMapping("/index")
    public String index(String smg){
        return smg;
    }
}
