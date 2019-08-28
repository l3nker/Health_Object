package com.itheima.dao;

import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface ExcleAddDao {

    Integer findCountByDate(OrderSetting orderSetting);

    void addNewExcle(OrderSetting orderSetting);

    void updateCountBydate(OrderSetting orderSetting);

    List<OrderSetting> searchDayAndMonth(Map<String, String> map);

    void editNumberByDate(OrderSetting orderSetting);
}
