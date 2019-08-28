package com.itheima.dao;

import com.itheima.pojo.User;

public interface SecurityUserDao {
    User searchUser(String username);
}
