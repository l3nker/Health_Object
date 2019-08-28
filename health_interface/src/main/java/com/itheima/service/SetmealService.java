package com.itheima.service;

import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    List<CheckGroup> findAll();

    void addNew(Integer[] checkgroupIds, Setmeal setmeal);
}
