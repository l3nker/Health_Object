package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.MobileWebService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class MobileWebContraller {

    @Reference
    private MobileWebService mobileWebService;

    @RequestMapping("/getSetmeal")
    public Result getSetmeal(){
        try {
            List<Setmeal> list= mobileWebService.findSetmeal();
            return new Result(true,"ok",list);
        }catch (Exception e){
            return new Result(false,"false");
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Setmeal setmeal= mobileWebService.findById(id);
            return new Result(true,"ok",setmeal);
        }catch (Exception e){
            return new Result(false,"false");
        }
    }

}
