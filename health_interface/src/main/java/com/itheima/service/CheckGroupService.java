package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckGroupService {
    void addGroup(Integer[] checkitemIds, CheckGroup checkGroup);

    PageResult findPages(Integer currentPage, Integer pageSize, String queryString);

    CheckGroup findUselForm(Integer id);

    List<CheckItem> findCheckItem();

    List<Integer> findCheckItemWhenUse(Integer id);

    void edit(Integer[] checkitemIds, CheckGroup checkGroup);
}
