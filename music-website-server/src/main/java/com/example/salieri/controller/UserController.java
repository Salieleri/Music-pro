package com.example.salieri.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.salieri.entity.User;
import com.example.salieri.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @ResponseBody
    @PostMapping("/user/login")
    public Object Login(@RequestBody JSONObject req,HttpSession session){
        JSONObject json = new JSONObject();
        String username = (String) req.get("username");
        String password = (String) req.get("password");

        int res = userService.selectByUsernameAndPasswd(username,password);

        if(res == 0){
            json.put("code",1);
            json.put("msg","账号或密码错误！");
        }
        else {
            json.put("code",0);
            json.put("msg","登陆成功！");
            json.put("usermsg", userService.selectByPrimaryKey(username));
            session.setAttribute("username",username);
        }
        return json;
    }

    @ResponseBody
    @PostMapping("/user/registry")
    public Object Registry(HttpServletRequest req){
        JSONObject json = new JSONObject();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phonenumber = req.getParameter("phonenumber");

        User newuser = new User();

        newuser.setUserId(username);
        newuser.setUserPassword(password);
        newuser.setUserPhonenumber(phonenumber);

        int res = userService.insertSelective(newuser);

        if(res > 0){
            json.put("code",0);
            json.put("msg","注册成功！");
        }
        else{
            json.put("code",1);
            json.put("msg","注册失败！");
        }
        return json;
    }

    @ResponseBody
    @PostMapping("/user/update")
    public Object UpdatePasswd(HttpServletRequest req){
        JSONObject json = new JSONObject();
        String password = req.getParameter("password");
        String username = req.getParameter("username");
        User curruser = userService.selectByPrimaryKey(username);
        if(curruser.getUserPassword().equals(password)) {
            json.put("code",1);
            json.put("msg","密码与原密码一致！");
        }
        else{
            curruser.setUserPassword(password);
            json.put("code",0);
            json.put("msg","修改密码成功！");
        }
        return json;
    }

    @ResponseBody
    @PostMapping("user/other")
    public Object OtherUser(HttpServletRequest req){
        JSONObject json = new JSONObject();
        String username = req.getParameter("username");
        User other = userService.selectByPrimaryKey(username);
        if(other != null){
            json.put("code",0);
            json.put("user",other);
        }
        else{
            json.put("code",1);
        }
        return json;
    }

    public Object deleteuser(@RequestBody JSONObject object){
        String username = (String)object.get("username");
        userService.deleteByPrimaryKey(username);

        JSONObject json = new JSONObject();
        json.put("code",1);

        return json;
    }
}
