package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    void add(CheckItem checkItem);

    Page<CheckItem> findPages(String queryString);

    long checkById(Integer id);

    void deleteById(Integer id);

    CheckItem searchItemById(Integer id);

    void compileData(CheckItem checkItem);

    List<CheckItem> findItem();
}
