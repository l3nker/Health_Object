package com.itheima.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.SecurityUserDao;
import com.itheima.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = SecurityUserService.class)
@Transactional
public class SecurityUserServiceImpl implements SecurityUserService {

    @Autowired
    private SecurityUserDao securityUserDao;

    @Override
    public User searchUser(String username) {
        User user=securityUserDao.searchUser(username);
        return user;
    }

}
