package com.example.salieri.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.salieri.entity.User;
import com.example.salieri.entity.model.AvatorUploadModel;
import com.example.salieri.entity.model.UserUpdateModel;
import com.example.salieri.service.impl.RedisService;
import com.example.salieri.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.example.salieri.constant.constant.RESOURCE_PATH_WIN;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RedisService redisService;

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @ResponseBody
    @GetMapping("/user")
    public List<Object> getalluser(){
        Set<String> set = redisService.keys("user");
        List<Object> res = redisService.mget(set);
        if(res.isEmpty()){
            res = Collections.singletonList(userService.getall());
        }
        return res;
    }

    @ResponseBody
    @PostMapping("/user/login")
    public Object Login(@RequestBody JSONObject req,HttpSession session){
        JSONObject json = new JSONObject();
        String username = (String) req.get("username");
        String password = (String) req.get("password");

        User res = (User) redisService.get("user", username);

        if (res == null){
            res = userService.selectByUsernameAndPasswd(username, password);
        }

        if(res == null || !res.getUserPassword().equals(password)){
            json.put("code",1);
            json.put("msg","账号或密码错误！");
        }
        else {
            redisService.set(res, "user", username);
            json.put("code",0);
            json.put("msg","登陆成功！");
            json.put("usermsg", res);
            session.setAttribute("username",username);
        }
        log.info("用户" + username + "登录成功");
        return json;
    }

    @ResponseBody
    @PostMapping("/user/registry")
    public Object Registry(@RequestBody JSONObject req){
        JSONObject json = new JSONObject();

        String username = (String) req.get("username");
        String password = (String) req.get("password");
        String phonenumber = (String) req.get("phonenum");
        String sex = (String) req.get("sex");

        User newuser = new User();
        newuser.setUserId(username);
        newuser.setUserPassword(password);
        newuser.setUserPhonenumber(phonenumber);
        newuser.setUserSex(sex);

        User conf = (User) redisService.get("user", username);
        if(conf != null){
            json.put("code",1);
            json.put("msg","已有此用户名的用户存在！");
            return json;
        }
        int res = userService.insertSelective(newuser);

        if(res > 0){
            redisService.set(newuser, "user", username);
            json.put("code",1);
            json.put("msg","注册成功！");
        }
        else{
            json.put("code",0);
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
        String sex = (String) req.get("sex");

        UserUpdateModel curruser = new UserUpdateModel();

        curruser.setUserIdBefore(before);
        curruser.setUserId(username);
        curruser.setUserPassword(password);
        curruser.setUserPhonenumber(phone);
        curruser.setUserSex(sex);


        int res = userService.updateByPrimaryKeySelective(curruser);

        if(res == 1){
            redisService.update("user", before);
            User arr = userService.selectByPrimaryKey(username);
            redisService.set(arr, "user", username);
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
                User user = (User) redisService.get("user", id);
                user.setUserAvator(storePath);
                redisService.update("user", id);
                redisService.set(user, "user", id);
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
    public Object deleteuser(HttpServletRequest req){
        String username = req.getParameter("id");
        String img = req.getParameter("img");
        userService.deleteByPrimaryKey(username);
        redisService.update("user", username);

        File Img = null;
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            Img = new File(RESOURCE_PATH_WIN + img);
        }

        if(Img.exists()){
            Img.delete();
        }

        JSONObject json = new JSONObject();
        json.put("code",1);

        return json;
    }

    @ResponseBody
    @GetMapping("/user/detail")
    public Object getuser(HttpServletRequest req){
        String id = req.getParameter("id");
        User user = (User) redisService.get("user", id);
        if(user == null){
            user = userService.selectByPrimaryKey(id);
            if(user != null)redisService.set(user, "user", id);
        }
        return user;
    }
}
