package com.itheima.dao;

import com.itheima.pojo.Member;
import com.itheima.pojo.Order;
import com.itheima.pojo.OrderSetting;
import com.itheima.pojo.Setmeal;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MobileWebDao {
    List<Setmeal> findSetmeal();

    Setmeal findById(Integer id);

    void updateOrderSetting(OrderSetting orderSetting);

    Map findMostById(Integer id);
}
