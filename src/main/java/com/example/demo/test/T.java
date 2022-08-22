package com.example.demo.test;

/**
 * @ClassName T
 * @Description
 * @Date 2022/4/12 13:42
 * @Author mxn
 * @Version 1.0
 */

public class T {

    public static void main(String[] args) {
        AInterface aInterface1 = new AImpl1();
        aInterface1.jkmCard("123");
        AInterface aInterface2 = new AImpl2();
        aInterface2.jkmCard("321");
    }
}
