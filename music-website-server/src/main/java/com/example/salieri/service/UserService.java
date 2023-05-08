package com.example.salieri.service;

import com.example.salieri.entity.User;
import com.example.salieri.entity.model.AvatorUploadModel;
import com.example.salieri.entity.model.UserUpdateModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public int deleteByPrimaryKey(String userId);

    public int insert(User record);

    public int insertSelective(User record);

    public User selectByPrimaryKey(String userId);

    public int updateByPrimaryKeySelective(UserUpdateModel record);

    public int updateByPrimaryKey(User record);

    public User selectByUsernameAndPasswd(String userId,String Passwd);

    public int updateAvator(AvatorUploadModel model);

    List<User> getall();
}
