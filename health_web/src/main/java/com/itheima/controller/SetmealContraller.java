package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constans.MessageConstant;
import com.itheima.constans.RedisConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import com.itheima.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetmealContraller {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/upload.do")
    public Result uploadImg( MultipartFile imgFile){

        try {
            String filename = imgFile.getOriginalFilename();
//            filename = UploadUtils.getUUIDName(filename);
            System.out.println(filename);
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),filename);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,filename);
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,filename);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    @RequestMapping("/findAll.do")
    public Result findAll( ){

        try {
           List<CheckGroup> list= setmealService.findAll();
            return new Result(true, "fuckyou",list);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"检查组集合查询失败");
        }
    }

    @RequestMapping("/addNew")
    public Result addNew(Integer[] checkgroupIds, @RequestBody Setmeal setmeal){

        try {
            setmealService.addNew(checkgroupIds,setmeal);
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }
}
