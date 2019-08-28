package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public void addGroup(Integer[] checkitemIds, CheckGroup checkGroup) {

        try {
            checkGroupDao.addGroupData(checkGroup);

        }catch (Exception e){
            System.out.println("addGroupData() error");
        }

        Integer checkGroupId = checkGroup.getId();

        try {
            Map<String,Integer> map =new HashMap();
            for (Integer checkitemId : checkitemIds) {
                map.put("checkGroupId",checkGroupId);
                map.put("checkitemId",checkitemId);
                checkGroupDao.addGroupIdAndItemId(map);
            }
        }catch (Exception e){
            System.out.println("addGroupIdAndItemId()  error");
        }



    }

    @Override
    public PageResult findPages(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);

        Page<CheckItem> page=checkGroupDao.findPages(queryString);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public CheckGroup findUselForm(Integer id) {
      CheckGroup checkGroup=   checkGroupDao.findUselForm(id);
        return checkGroup;
    }


    @Override
    public List<CheckItem> findCheckItem() {
      List<CheckItem> list=  checkGroupDao.findCheckItem();
        return list;
    }

    @Override
    public List<Integer> findCheckItemWhenUse(Integer id) {
        List<Integer> checkitemId=  checkGroupDao.findCheckItemWhenUse(id);
        return checkitemId;
    }

    @Override
    public void edit(Integer[] checkitemIds, CheckGroup checkGroup) {
        //删除原有数据
        checkGroupDao.deleteKey(checkGroup.getId());

        //更新数据
        updateNewDate(checkitemIds,checkGroup.getId());

        //更新主表数据
        checkGroupDao.updateGroupFrom(checkGroup);

    }

    private void updateNewDate(Integer[] checkitemIds,Integer checkGroupId){

        Map<String,Integer> map=new HashMap<>();

        if(checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                map.put("checkGroupId", checkGroupId);
                map.put("checkitemId", checkitemId);
                checkGroupDao.insertByGroupId(map);
            }
        }
    }
}
