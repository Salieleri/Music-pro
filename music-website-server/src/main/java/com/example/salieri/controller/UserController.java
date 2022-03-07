package com.example.salieri.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.salieri.entity.User;
import com.example.salieri.entity.model.AvatorUploadModel;
import com.example.salieri.entity.model.UserUpdateModel;
import com.example.salieri.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;

import static com.example.salieri.constant.constant.RESOURCE_PATH_WIN;

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
            json.put("usermsg",userService.selectByPrimaryKey(username));
            session.setAttribute("username",username);
        }
        return json;
    }

    @ResponseBody
    @PostMapping("/user/registry")
    public Object Registry(@RequestBody JSONObject req){
        JSONObject json = new JSONObject();

        String username = (String) req.get("username");
        String password = (String) req.get("password");
        String phonenumber = (String) req.get("phonenum");

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
    public Object UpdatePasswd(@RequestBody JSONObject req){
        JSONObject json = new JSONObject();
        String before = (String) req.get("before");
        String password = (String) req.get("password");
        String username = (String) req.get("id");
        String phone = (String) req.get("phone");

        UserUpdateModel curruser = new UserUpdateModel();

        curruser.setUserIdBefore(before);
        curruser.setUserId(username);
        curruser.setUserPassword(password);
        curruser.setUserPhonenumber(phone);

        int res = userService.updateByPrimaryKeySelective(curruser);

        if(res == 1){
            json.put("code", 1);
        } else {
            json.put("code", 0);
        }
        return json;
    }

    @ResponseBody
    @PostMapping("/user/avator/upload")
    public Object UpdateAvator(@RequestParam("file") MultipartFile avatorfile,
                               @RequestParam("id") String id,
                               @RequestParam("past") String past){
        JSONObject res = new JSONObject();

        if(avatorfile.isEmpty()){
            res.put("code", 0);
            res.put("mes", "文件为空!");
            return res;
        }

        String fileName = System.currentTimeMillis() + id + ".jpg";
        String filePath = "";
        String storePath = "/img/Avator/" + fileName;

        File fileofpast = null;
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            filePath = RESOURCE_PATH_WIN + "\\img\\Avator\\";
            fileofpast = new File(RESOURCE_PATH_WIN + System.getProperty("file.separator") + past);
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        if(fileofpast.exists()){
            fileofpast.delete();
        }
        try {
            avatorfile.transferTo(dest);
            AvatorUploadModel model = new AvatorUploadModel();
            model.setUserid(id);
            model.setAvator(storePath);
            int tot = userService.updateAvator(model);
            if(tot != 0) {
                res.put("code", 1);
                res.put("mes", "上传成功");
                res.put("avator", storePath);
            }
            else {
                res.put("code", 0);
                res.put("mes", "上传失败!");
            }
        } catch(IOException e){
            res.put("code", 0);
            res.put("mes", "上传失败!");
            return res;
        }
        return res;
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

    @ResponseBody
    @GetMapping("/user/delete")
    public Object deleteuser(@RequestBody JSONObject object){
        String username = (String)object.get("username");
        userService.deleteByPrimaryKey(username);

        JSONObject json = new JSONObject();
        json.put("code",1);

        return json;
    }

    @ResponseBody
    @GetMapping("/user/detail")
    public Object getuser(HttpServletRequest req){
        String id = req.getParameter("id");
        return userService.selectByPrimaryKey(id);
    }
}
