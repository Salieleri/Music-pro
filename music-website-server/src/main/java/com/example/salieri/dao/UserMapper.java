package com.example.salieri.dao;

import com.example.salieri.entity.User;
import com.example.salieri.entity.model.AvatorUploadModel;
import com.example.salieri.entity.model.UserUpdateModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserUpdateModel record);

    int updateByPrimaryKey(User record);

    int selectByUsernameAndPasswd(String userId,String Passwd);

    int updateAvator(AvatorUploadModel model);

}
