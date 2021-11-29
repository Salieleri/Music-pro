package com.example.salieri.service.impl;

import com.example.salieri.dao.UserMapper;
import com.example.salieri.entity.User;
import com.example.salieri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public int deleteByPrimaryKey(String userId){
        return 0;
    }

    public int insert(User record){
        return 0;
    }

    public int insertSelective(User record){
        return 0;
    }

    public User selectByPrimaryKey(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    public int updateByPrimaryKey(User record) {
        return 0;
    }

    public int selectByUsernameAndPasswd(String userId, String Passwd) {
        return userMapper.selectByUsernameAndPasswd(userId, Passwd);
    }

}
