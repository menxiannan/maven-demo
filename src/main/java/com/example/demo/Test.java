package com.example.demo;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * @ClassName Test
 * @Description
 * @Date 2022/2/24 9:32
 * @Author mxn
 * @Version 1.0
 */

public class Test {


    public static void main(String[] args) {

        String jsonStr = "[{\"msg\":\"套餐为空\",\"phone\":\"13720535555\"},{\"msg\":\"实名人为空\",\"phone\":\"19916269999\"},{\"msg\":\"实名人为空\",\"phone\":\"18291412888\"},{\"msg\":\"套餐为空\",\"phone\":\"18768161888\"},{\"msg\":\"套餐为空\",\"phone\":\"15158065522\"},{\"msg\":\"套餐为空\",\"phone\":\"15157175500\"},{\"msg\":\"套餐为空\",\"phone\":\"15158016161\"},{\"msg\":\"实名人为空\",\"phone\":\"15858243588\"},{\"msg\":\"套餐为空\",\"phone\":\"15858243688\"},{\"msg\":\"套餐为空\",\"phone\":\"15068126817\"},{\"msg\":\"套餐为空\",\"phone\":\"18721016555\"},{\"msg\":\"套餐为空\",\"phone\":\"13750810868\"},{\"msg\":\"套餐为空\",\"phone\":\"13750811068\"},{\"msg\":\"套餐为空\",\"phone\":\"13750823168\"}]";

        List<Map<String, String>> list = JSONObject.parseObject(jsonStr, List.class);

        for (Map<String, String> map : list) {
            String phone = map.get("phone");
            System.out.println(phone+"\t"+map.get("msg"));
        }


    }

}
