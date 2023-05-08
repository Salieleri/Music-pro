package com.example.salieri.service.impl;

import com.example.salieri.dao.AdminMapper;
import com.example.salieri.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean login(String Admin, String pswd) {
        return adminMapper.login(Admin, pswd) > 0;
    }
}
