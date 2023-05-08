package com.example.salieri.service.impl;

import com.example.salieri.dao.UserMapper;
import com.example.salieri.entity.User;
import com.example.salieri.entity.model.AvatorUploadModel;
import com.example.salieri.entity.model.UserUpdateModel;
import com.example.salieri.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public int deleteByPrimaryKey(String userId){
        return userMapper.deleteByPrimaryKey(userId);
    }

    public int insert(User record){
        return 0;
    }

    public int insertSelective(User record){
        return userMapper.insertSelective(record);
    }

    @Override
    public int updateAvator(AvatorUploadModel model) {
        return userMapper.updateAvator(model);
    }

    public User selectByPrimaryKey(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public int updateByPrimaryKeySelective(UserUpdateModel record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<User> getall() {
        return userMapper.getall();
    }

    public int updateByPrimaryKey(User record) {
        return 0;
    }

    public User selectByUsernameAndPasswd(String userId, String Passwd) {
        return userMapper.selectByUsernameAndPasswd(userId, Passwd);
    }

}
