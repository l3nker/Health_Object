package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.ArrayList;
import java.util.List;

public interface ExcleAddService {
    void addNewExcle(ArrayList<OrderSetting> orderSettings);

    List<OrderSetting> searchDayAndMonth(String date);

    void editNumberByDate(OrderSetting orderSetting);
}
