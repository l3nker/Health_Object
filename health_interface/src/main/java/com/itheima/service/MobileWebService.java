package com.itheima.service;

import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface MobileWebService {
    List<Setmeal> findSetmeal();

    Setmeal findById(Integer id);

    Result orderSubmit(Map<String, Object> map) throws Exception;

    Map findMostById(Integer id);
}
