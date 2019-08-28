package com.itheima.dao;

import com.itheima.pojo.Member;

import java.util.Map;

public interface MemberDao {

    Member searchMemberByPhoneNumber(String phoneNumber);

    void addMember(Member member);


    public Integer findMemberCountBeforeDate(String date);
    public Integer findMemberCountByDate(String date);
    public Integer findMemberCountAfterDate(String date);
    public Integer findMemberTotalCount();

}
