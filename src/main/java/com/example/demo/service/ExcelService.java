package com.example.demo.service;

import com.example.demo.domain.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName ExcelService
 * @Description
 * @Date 2021/11/22 10:03
 * @Author mxn
 * @Version 1.0
 */
@Service
@Slf4j
public class ExcelService {


    public List<UserVO> exportExcelData(){
        List<UserVO> data = new ArrayList<>();


        UserVO vo;
        for (int i = 0; i < 100; i++) {
            vo = new UserVO("张三" + i, "1566666666" + i, new Date());

            data.add(vo);
        }
        return data;

    }

}
