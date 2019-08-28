package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    public void add(CheckItem checkItem);

    PageResult findPages(Integer currentPage, Integer pageSize, String queryString);

    void deleteById(Integer id);

    CheckItem serchItemById(Integer id);

    void compileData(CheckItem checkItem);

    List<CheckItem> findItem();
}
