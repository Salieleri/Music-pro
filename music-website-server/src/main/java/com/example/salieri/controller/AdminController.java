package com.example.salieri.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.salieri.entity.Admin;
import com.example.salieri.service.impl.AdminServiceImpl;
import com.example.salieri.service.impl.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @PostMapping("/admin/login/status")
    public Object login(@RequestBody JSONObject json){
        String admin = (String) json.get("admin");
        String pswd = (String) json.get("pswd");

        Integer res = (Integer) redisService.get("admin", admin, pswd);

        if(res == null){
            res = adminService.login(admin, pswd) ? 1 : 0;
        }

        JSONObject ret = new JSONObject();

        if(res != 0){
            redisService.set(1, "admin", admin, pswd);
            ret.put("code", 1);
        } else {
            ret.put("code", 0);
        }
        return ret;
    }
}
