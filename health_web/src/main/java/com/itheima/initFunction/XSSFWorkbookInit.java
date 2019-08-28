package com.itheima.initFunction;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.math.BigDecimal;
import java.util.Map;

public class XSSFWorkbookInit {
    private final XSSFWorkbook workbook;
    private ExcleDataInit excleData;


    public XSSFWorkbookInit(XSSFWorkbook workbook,ExcleDataInit excleData) {
        this.workbook = workbook;
        this.excleData=excleData;



        XSSFSheet sheet = workbook.getSheetAt(0);
        //3.取出数据, 向Excel里面插入数据
        XSSFRow row = sheet.getRow(2);

        row.getCell(5).setCellValue(excleData.getReportDate());//日期

        row = sheet.getRow(4);
        row.getCell(5).setCellValue(excleData.getTodayNewMember());//新增会员数（本日）
        row.getCell(7).setCellValue(excleData.getTotalMember());//总会员数

        row = sheet.getRow(5);
        row.getCell(5).setCellValue(excleData.getThisWeekNewMember());//本周新增会员数
        row.getCell(7).setCellValue(excleData.getThisMonthNewMember());//本月新增会员数

        row = sheet.getRow(7);
        row.getCell(5).setCellValue(excleData.getTodayOrderNumber());//今日预约数
        row.getCell(7).setCellValue(excleData.getTodayVisitsNumber());//今日到诊数

        row = sheet.getRow(8);
        row.getCell(5).setCellValue(excleData.getThisWeekOrderNumber());//本周预约数
        row.getCell(7).setCellValue(excleData.getThisWeekVisitsNumber());//本周到诊数

        row = sheet.getRow(9);
        row.getCell(5).setCellValue(excleData.getThisMonthOrderNumber());//本月预约数
        row.getCell(7).setCellValue(excleData.getThisMonthVisitsNumber());//本月到诊数

        int rowNum = 12;
        for(
                Map map : excleData.getHotSetmeal()){//热门套餐
            String name = (String) map.get("name");
            Long setmeal_count = (Long) map.get("setmeal_count");
            BigDecimal proportion = (BigDecimal) map.get("proportion");
            row = sheet.getRow(rowNum ++);
            row.getCell(4).setCellValue(name);//套餐名称
            row.getCell(5).setCellValue(setmeal_count);//预约数量
            row.getCell(6).setCellValue(proportion.doubleValue());//占比
        }
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }
}
