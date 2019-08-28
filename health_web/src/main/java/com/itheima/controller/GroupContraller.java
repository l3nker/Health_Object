package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constans.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;
import com.itheima.service.CheckGroupService;
import com.itheima.service.CheckItemService;
import com.itheima.service.SetmealService;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController   ///checkItem/findPages.do
@RequestMapping("/checkGroup")
public class GroupContraller {

    @Reference
    private CheckItemService chekItemService;

    @Reference
    private CheckGroupService checkGroupService;

    @RequestMapping("/findItem")
    public Result findItem(){

        try {
          List<CheckItem> list= chekItemService.findItem();

          if (list==null){
              return new Result(false,"没有查询到数据");
          }
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS,list);
        }catch(Exception e){
            return new Result(false,"查询数据发生了异常");
        }

    }


    @RequestMapping("/addGroup")
    public Result addGroup(Integer[] checkitemIds,@RequestBody CheckGroup checkGroup){

        try {
            checkGroupService.addGroup(checkitemIds,checkGroup);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,"Contraller添加检查组异常");
        }

    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){

        System.err.println("in findPage contraller");

        PageResult pageResult= checkGroupService.findPages(queryPageBean.getCurrentPage(),queryPageBean.getPageSize(),queryPageBean.getQueryString());

        return pageResult;
    }

    @RequestMapping("/findUselForm")
    public Result findUselForm(Integer id){


        try {
            CheckGroup checkGroup= checkGroupService.findUselForm(id);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS,checkGroup);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,"Contraller添加检查组异常");
        }
    }

    @RequestMapping("/findCheckItem")
    public Result findCheckItem(){


        try {
          List<CheckItem> list= checkGroupService.findCheckItem();
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS,list);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,"Contraller添加检查组异常");
        }
    }

    @RequestMapping("/findCheckItemWhenUse")
    public Result findCheckItemWhenUse(Integer id){


        try {
      List<Integer> checkitemId=  checkGroupService.findCheckItemWhenUse(id);
            System.out.println(checkitemId);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS,checkitemId);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,"Contraller添加检查组异常");
        }
    }
    @RequestMapping("/edit")
    public Result edit(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup ){
        try {
            checkGroupService.edit(checkitemIds,checkGroup);
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false,"edit编辑数据失败！");
        }
    }

}
