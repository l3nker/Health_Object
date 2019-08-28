package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.MobileWebService;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RestController
@RequestMapping("/validateCode")
public class OrderContraller {

    @Reference
    private MobileWebService mobileWebService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/send4Order")
    public Result getSetmeal(String telephone){
        try {

            Integer integer = ValidateCodeUtils.generateValidateCode(6);

            jedisPool.getResource().setex(SMSUtils.VALIDATE_CODE+"_"+telephone,60*5,integer.toString());
            return new Result(true,"ok");
        }catch (Exception e){
            return new Result(false,"false");
        }
    }

}
