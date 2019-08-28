package com.itheima.dao;

import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    List<CheckGroup> findAll();

    void addSetmeal(Setmeal setmeal);

    void setID(Map<String, Integer> map);
}
