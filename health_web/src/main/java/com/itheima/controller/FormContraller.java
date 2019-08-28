package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constans.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.service.CheckItemService;
import java.util.List;

@RestController   ///checkItem/findPages.do
@RequestMapping("/checkItem")
public class FormContraller {

    @Reference
    private CheckItemService chekItemService;

    @RequestMapping("/add")
    public Result addForm(@RequestBody CheckItem checkItem){

        System.out.println("in this controller");
        try {
            chekItemService.add(checkItem);
        }catch (Exception e){
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/findPages")
    public PageResult findPages(@RequestBody QueryPageBean queryPageBean){

        System.out.println(queryPageBean);

        PageResult pageResult= chekItemService.findPages(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());

        return pageResult;
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAnyAuthority('CHECKITEM_DELETE')")
    public Result findPages(Integer id){

        try {
          chekItemService.deleteById(id);
            System.out.println("id=" + id);
            return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }catch (Exception e){
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/compile")
    public Result compileForm(Integer id){

        try {
            CheckItem checkItem= chekItemService.serchItemById(id);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS,checkItem);
        }catch(Exception e){
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    @RequestMapping("/compileData")
    public Result compileData(@RequestBody CheckItem checkItem){

        try {
           chekItemService.compileData(checkItem);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        }catch(Exception e){
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }
}
