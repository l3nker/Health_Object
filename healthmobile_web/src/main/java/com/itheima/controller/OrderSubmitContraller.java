package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constans.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.service.MobileWebService;
import com.itheima.utils.SMSUtils;
import com.itheima.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;
import sun.security.jgss.spi.MechanismFactory;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderSubmitContraller {
    @Reference
    private MobileWebService mobileWebService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/submit")
    public Result submit(@RequestBody Map<String,Object> map){
        try {
//            1. 点击提交预约, 把用户信息 提交到服务器
            String telephone = (String) map.get("telephone");
            String validateCode = (String) map.get("validateCode");
//            2. 在Controller里面

//            String s = jedisPool.getResource().get(SMSUtils.VALIDATE_CODE + "_" + telephone);

//            if (s==null||s.equals(validateCode)){
//                return new Result(false,"validate error");
//            }

            Result result=mobileWebService.orderSubmit(map);

            return result;
        }catch (Exception e){
            return new Result(false,"false");
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
          Map map= mobileWebService.findMostById(id);
          System.out.println(map.toString());
          return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS,map);
        }catch (Exception e){
          return new Result(false,MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }
}
