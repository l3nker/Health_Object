package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constans.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.ExcleAddService;
import com.itheima.utils.POIUtils;
import com.sun.org.apache.regexp.internal.RE;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class ExcleContraller {

    @Reference
   private ExcleAddService excleAddService;

    @RequestMapping("/upload")
    public Result uploadOrder(MultipartFile excelFile) throws IOException {

        List<String[]> lists = POIUtils.readExcel(excelFile);
        ArrayList<OrderSetting> orderSettings = new ArrayList<>();

        try {

            if (lists!=null&&lists.size()>0) {
                for (String[] array : lists) {
                    OrderSetting setting = new OrderSetting(new Date(array[0]),  Integer.parseInt(array[1]));
                    orderSettings.add(setting);
                }
            }
            excleAddService.addNewExcle(orderSettings);
            return new Result(true, MessageConstant.UPLOAD_SUCCESS);
        }catch (Exception e){
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    @RequestMapping("/getOrderSettingByMonth")
    public Result searchDayAndMonth(String date){

        System.out.println("in here"+date);
       try {
          List<OrderSetting> list= excleAddService.searchDayAndMonth(date);
           System.out.println(list);
           return new Result(true,MessageConstant.EDIT_MEMBER_FAIL,list);
       }catch (Exception e){

           return new Result(false,MessageConstant.EDIT_MEMBER_FAIL);
       }
    }

    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try {
            excleAddService.editNumberByDate(orderSetting);
            return new Result(true,MessageConstant.EDIT_MEMBER_FAIL);
        }catch (Exception e){

            return new Result(false,MessageConstant.EDIT_MEMBER_FAIL);
        }
    }
}
