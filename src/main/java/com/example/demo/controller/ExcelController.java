package com.example.demo.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.example.demo.domain.UserVO;
import com.example.demo.service.ExcelService;
import com.example.demo.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * @ClassName ExcelController
 * @Description
 * @Date 2021/11/22 10:01
 * @Author mxn
 * @Version 1.0
 */
@Slf4j
@RestController
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    /**
     * 导出Excel
     *
     * @param response
     */
    @RequestMapping("/excel/export")
    public void exportExcel(HttpServletResponse response) {
        List<UserVO> userVOS = excelService.exportExcelData();

        try {

            // 每个ExcelExportEntity存放Map行数据的key
            List<ExcelExportEntity> keyList = new ArrayList<>();
            ExcelExportEntity excelExportEntity = new ExcelExportEntity("会员昵称", "userName");
            keyList.add(excelExportEntity);
            excelExportEntity = new ExcelExportEntity("电话", "phone");
            keyList.add(excelExportEntity);

            List<Map<String, Object>> rowDataList = new ArrayList<>();
            Map<String, Object> rowMap;
            for (UserVO item : userVOS) {
                // 一个Map对应一行数据（如果需要导出多行数据，那么需要多个Map）
                rowMap = new HashMap<>(32);
                rowMap.put("userName", item.getUserName());
                rowMap.put("phone", item.getPhone());
                rowMap.put("date", DateUtils.format(item.getDate(), DateUtils.DATE_TIME_PATTERN));
                rowDataList.add(rowMap);
            }

            // 导出时间
            String exportDate = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
            // excel总体设置
            ExportParams exportParams = new ExportParams("导出时间" + exportDate, "订单统计");
            exportParams.setAutoSize(true);
            exportParams.setHeaderColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
            exportParams.setColor(HSSFColor.HSSFColorPredefined.RED.getIndex());

            // 生成workbook 并导出
            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, keyList, rowDataList);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            workbook.write(bos);
            byte[] barray = bos.toByteArray();
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=" + exportDate + ".xls");
            response.addHeader("Content-Length", "" + barray.length);
            response.setContentType("application/octet-stream; charset=UTF-8");
            IOUtils.write(barray, response.getOutputStream());
        } catch (Exception e) {
            log.error("ExcelController.exportExcel error:{}", e);
        }

    }


}
