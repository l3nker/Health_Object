package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constans.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.initFunction.ExcleDataInit;
import com.itheima.initFunction.StreamInit;
import com.itheima.initFunction.XSSFWorkbookInit;
import com.itheima.service.ReportService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportContraller {

    @Reference
    private ReportService reportService;

    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
        //使用map对数据进行分装，两个数据分别由dao获得 setmealNames setmealCount
        try {
            List<String> mealNames = reportService.searchMealNames();

            List<Map> mealCount = reportService.searchMealCount();

            System.out.println("mealNames = " + mealNames);

            System.out.println("mealCount = " + mealCount);

            Map<String,List> map=new HashMap<>();

            map.put("setmealNames",mealNames);
            map.put("setmealCount",mealCount);

            return new Result(true,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,null);
        }
    }

    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData(){
        try {
            Map<String, Object> map = reportService.getBusinessReportData();
            return new Result(true, MessageConstant.GET_BUSINESS_REPORT_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }

    @RequestMapping("/exportBusinessReport")
    public Result exportBusinessReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
//         StreamInit streamInit =null;
        XSSFWorkbook workbookInit=null;
        InputStream is=null;
        OutputStream os=null;

        is = request.getSession().getServletContext().getResourceAsStream("template/report_template.xlsx");
        os = response.getOutputStream();

//        streamInit = new StreamInit(request, response);//初始化流

        ExcleDataInit  excleData = new ExcleDataInit(reportService.getBusinessReportData());//初始化数据库参数

        workbookInit = new XSSFWorkbookInit(new XSSFWorkbook(is), excleData).getWorkbook();//初始化抽象excle数据

        try {

            response.setHeader("Content-Disposition","attachment;filename=report.xlsx"); //告诉浏览器去下载
            response.setHeader("Content-Type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            workbookInit.write(os);//流写出

            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                if(os!= null){
                   os.close();
                }
                if(is != null){
                    is.close();
                }
                if(workbookInit != null){
                    workbookInit.close();
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }



}
