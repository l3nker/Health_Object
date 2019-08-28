package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    void addGroupData(CheckGroup checkGroup);

    void addGroupIdAndItemId(Map<String, Integer> map);

    Page<CheckItem> findPages(String queryString);

    CheckGroup findUselForm(Integer id);

    List<CheckItem> findCheckItem();

    List<Integer> findCheckItemWhenUse(Integer id);

    void deleteKey(Integer id);

    void insertByGroupId(Map<String, Integer> map);

    void updateGroupFrom(CheckGroup checkGroup);
}
