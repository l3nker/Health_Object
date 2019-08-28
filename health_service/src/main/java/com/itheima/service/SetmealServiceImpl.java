package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.constans.RedisConstant;
import com.itheima.dao.SetmealDao;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService   {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public List<CheckGroup> findAll() {
      List<CheckGroup> list=  setmealDao.findAll();
        return list;
    }

    @Override
    public void addNew(Integer[] checkgroupIds, Setmeal setmeal) {
        setmealDao.addSetmeal(setmeal);
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
        addGroupIds(checkgroupIds,setmeal.getId());
    }


    private void addGroupIds(Integer[] checkgroupIds, Integer setmealId) {
        Map<String, Integer> map = new HashMap<>();
        if (checkgroupIds!=null&&checkgroupIds.length>0){
            for (Integer checkgroupId : checkgroupIds) {
                map.put("setmealId",setmealId);
                map.put("checkgroupId",checkgroupId);
                setmealDao.setID(map);
            }
        }
    }
}
