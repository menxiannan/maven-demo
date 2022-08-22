package com.example.demo.test;

/**
 * @ClassName AImpl1
 * @Description
 * @Date 2022/4/12 13:41
 * @Author mxn
 * @Version 1.0
 */

public class AImpl1 implements AInterface{

    @Override
    public String jkmCard(String cardId) {
        System.out.println("AImpl1");
        return cardId;
    }
}
