package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.ExcleAddDao;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = ExcleAddService.class)
@Transactional
public class ExcleAddServiceImpl implements ExcleAddService {

    @Autowired
    private ExcleAddDao excleAddDao;

    @Override
    public void addNewExcle(ArrayList<OrderSetting> orderSettings) {
        if (orderSettings!=null&&orderSettings.size()>0) {
            for (OrderSetting orderSetting : orderSettings) {
               Integer count= excleAddDao.findCountByDate(orderSetting);

               if (count>0){
                   excleAddDao.addNewExcle(orderSetting);
               }else {
                   excleAddDao.updateCountBydate(orderSetting);
               }
            }
        }


    }

    @Override
    public List<OrderSetting> searchDayAndMonth(String date) {
        String firtDay=date+"-1";
        String lastDay=date+"-31";
        Map<String,String> map=new HashMap<>();
        map.put("firtDay",firtDay);
        map.put("lastDay",lastDay);
        List<OrderSetting> list=  excleAddDao.searchDayAndMonth(map);
        return list;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        excleAddDao.editNumberByDate(orderSetting);
    }
}
