package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
   private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult findPages(Integer currentPage, Integer pageSize, String queryString) {

        PageHelper.startPage(currentPage,pageSize);

        Page<CheckItem> page=checkItemDao.findPages(queryString);

        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void deleteById(Integer id) {

      long count=  checkItemDao.checkById(id);
        if (count>0) {
            throw new RuntimeException("该项已经被检查组引用，删除失败！");
        }
            checkItemDao.deleteById(id);
    }

    @Override
    public CheckItem serchItemById(Integer id) {
       CheckItem checkItem= checkItemDao.searchItemById(id);
        return checkItem;
    }

    @Override
    public void compileData(CheckItem checkItem) {
        checkItemDao.compileData(checkItem);
    }

    @Override
    public List<CheckItem> findItem() {
        System.err.println("进入了服务器");

        try {

            List<CheckItem> list=  checkItemDao.findItem();
            return list;
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Service查询数据发生了异常");
        }
        return null;
    }
}
