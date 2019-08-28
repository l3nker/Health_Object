package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.constans.MessageConstant;
import com.itheima.dao.MemberDao;
import com.itheima.dao.MobileWebDao;
import com.itheima.dao.OrderDao;
import com.itheima.entity.Result;
import com.itheima.pojo.*;
import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = MobileWebService.class)
@Transactional
public class MobileWebServiceImpl implements MobileWebService {

    @Autowired
    private MobileWebDao mobileWebDao;

    @Autowired
    private MemberDao memberDao;//会员信息

    @Autowired
    private OrderDao orderDao;//预约信息

    @Override
    public List<Setmeal> findSetmeal() {

        List<Setmeal> list=  mobileWebDao.findSetmeal();
        return list;
    }

    @Override
    public Setmeal findById(Integer id) {

        Setmeal setmeal= mobileWebDao.findById(id);
        return setmeal;
    }

    @Override
    public Result orderSubmit(Map<String, Object> map) throws Exception {
        String  orderDateStr = (String) map.get("orderDate");//预约日期
        String  setmealId = (String) map.get("setmealId");//套餐ID

        //生成预约会员信息
        Member member = generatedMember(map);


        //判断预约日期合法性
        OrderSetting orderSetting=null;

        try {
            orderSetting = searchOrderDay(orderDateStr);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }


        //更新会员信息
        try {
            setMember(orderDateStr,setmealId,member);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }

        //更新预约信息
        Order order=null;
        try {
            order = setOrderData(member, orderDateStr, setmealId);
        }catch (Exception e){
            System.out.println("预约信息更新失败！");
        }

        //更新预约设置
        try {
            setOrderSetting(orderSetting);
        }catch (Exception e){
            System.out.println("预约设置更新失败！");
        }
        return  new Result(true,MessageConstant.ORDER_SUCCESS,order);
    }

    @Override
    public Map findMostById(Integer id) {

        return mobileWebDao.findMostById(id);

    }


    public Member generatedMember(Map<String, Object> map){

        Member  member = new Member();
        member.setName((String) map.get("name"));
        member.setPhoneNumber((String) map.get("telephone"));
        member.setIdCard((String) map.get("idCard"));
        member.setSex((String) map.get("sex"));
        member.setRegTime(new Date());
        return member;
    }

    public Order setOrderData(Member member,String orderDateStr,String setmealId) throws Exception {
        Order order = new Order(member.getId(),
                DateUtils.parseString2Date(orderDateStr),
                "微信预约",
                Order.ORDERSTATUS_NO,
                Integer.parseInt(setmealId));

        orderDao.addNewOrder(order);
        return order;
    }

    //更新预约设置
    public void setOrderSetting(OrderSetting orderSetting){

        orderSetting.setReservations(orderSetting.getReservations()+1);

        mobileWebDao.updateOrderSetting(orderSetting);
    }

    //操作数据库中的会员表
    public void setMember(String orderDateStr,String setmealId,Member member) throws Exception {

        Member t_member= memberDao.searchMemberByPhoneNumber(member.getPhoneNumber());

//                - 是会员, 避免重复预约(根据会员id, 预约时间orderDate, 套餐id查询t_order)
        if (t_member!=null) {
            Order order = new Order(t_member.getId(), DateUtils.parseString2Date(orderDateStr), null, null, Integer.parseInt(setmealId));

            List<Order> list = orderDao.findByCondition(order);

            //如果重复预约则预约失败
            if(list != null && list.size()>0){
                throw new RuntimeException("当前预约已存在，不能重复预约");
            }
        }else {
            //注册会员
            memberDao.addMember(member);
        }
    }

    //判断日期合法性

    public OrderSetting searchOrderDay(String orderDateStr) throws Exception {

        //        1. 判断预约日期合法性

        OrderSetting orderSetting = orderDao.searchDateByOrderDay(DateUtils.parseString2Date(orderDateStr));

        if(orderSetting == null){
            throw new RuntimeException( MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

//        2. 判断当前日期是否预约已满(判断reservations是否等于number)

        if (orderSetting.getReservations()==orderSetting.getNumber()) {
            throw new RuntimeException( MessageConstant.ORDER_FULL);
        }

        return orderSetting;
    }
}
