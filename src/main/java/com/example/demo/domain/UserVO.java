package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName UserVO
 * @Description
 * @Date 2021/11/22 10:07
 * @Author mxn
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {

    private String userName;
    private String phone;
    private Date date;
}
