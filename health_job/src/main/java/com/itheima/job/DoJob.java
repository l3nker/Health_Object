package com.itheima.job;

import com.itheima.constans.RedisConstant;
import com.itheima.utils.QiniuUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class DoJob {

    @Autowired
    private JedisPool jedisPool;


    public void run(){

        System.out.println("delete img!");
        Set<String> sets = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);

        if (sets!=null&&sets.size()>0) {
            for (String set : sets) {
                System.out.println("delete : "+set);
                QiniuUtils.deleteFileFromQiniu(set);
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES,set);
            }
        }

    }

}
