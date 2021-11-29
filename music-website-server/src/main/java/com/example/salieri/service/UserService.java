package com.example.salieri.service;

import com.example.salieri.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public int deleteByPrimaryKey(String userId);

    public int insert(User record);

    public int insertSelective(User record);

    public User selectByPrimaryKey(String userId);

    public int updateByPrimaryKeySelective(User record);

    public int updateByPrimaryKey(User record);

    public int selectByUsernameAndPasswd(String userId,String Passwd);
}
