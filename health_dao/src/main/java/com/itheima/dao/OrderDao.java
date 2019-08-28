package com.itheima.dao;

import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderDao {
    OrderSetting searchDateByOrderDay(Date parseString2Date);

    List<Order> findByCondition(Order order);

    void addNewOrder(Order order);


    public Integer findOrderCountByDate(String date);
    public Integer findOrderCountAfterDate(String date);
    public Integer findVisitsCountByDate(String date);
    public Integer findVisitsCountAfterDate(String date);
    public List<Map> findHotSetmeal();
    List<Map> findSetmealCount();
}
