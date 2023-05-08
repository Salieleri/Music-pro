package com.example.salieri.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.salieri.entity.CollectionKey;
import com.example.salieri.entity.Song;
import com.example.salieri.entity.model.DeleteCollectModel;
import com.example.salieri.service.impl.CollectionServiceImpl;
import com.example.salieri.service.impl.RedisService;
import com.example.salieri.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CollectionController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private CollectionServiceImpl collectionService;

    @Autowired
    private SongServiceImpl songService;

    // 确认是否收藏
    @ResponseBody
    @PostMapping("/collect/confirm")
    public Object confirm(@RequestBody JSONObject req){
        JSONObject json = new JSONObject();
        String userid = (String) req.get("userid");
        Integer songid = (Integer) req.get("id");
        boolean confirm = redisService.sismember(String.valueOf(songid), "Collect", userid);
        if(confirm){
            json.put("code", 1);
        } else {
            boolean isexist = collectionService.confirm(userid, songid);
            if(isexist){
                redisService.set(String.valueOf(songid) ,"Collect", userid);
                json.put("code", 1);
            } else {
                json.put("code", 0);
            }
        }
        return json;
    }

    // 获取收藏列表
    @ResponseBody
    @GetMapping("/collect/get")
    public List<?> getcollection(HttpServletRequest req){
        String id = req.getParameter("id");
        if(id.isEmpty())return null;
        Set<String> set = redisService.smembers("Collect", id);
        List<Integer> collectid;
        if(set == null){
            collectid = collectionService.getcollection(id);
            if(collectid.isEmpty())return null;
            return songService.getsongById(collectid);
        } else {
            Set<String> args = new HashSet<>();
            for(String i: set){
                args.add("Song_" + i + '_');
            }
            return redisService.mget(args);
        }
    }

    // 添加收藏
    @ResponseBody
    @PostMapping("/collect/add")
    public Object setcollection(@RequestBody JSONObject json){
        JSONObject res = new JSONObject();
        String userid = (String) json.get("userid");
        Integer id = (Integer) json.get("songid");
        Integer type = (Integer) json.get("type");

        CollectionKey curr = new CollectionKey();
        curr.setUserId(userid);
        curr.setId(id);
        curr.setType(type);

        if(collectionService.addcollection(curr) > 0){
            redisService.sadd(String.valueOf(id), "Collect", userid);
            res.put("code", 1);
        } else {
            res.put("code", 0);
        }
        return res;
    }

    // 删除收藏
    @ResponseBody
    @PostMapping("/collect/del")
    public Object delcollection(@RequestBody JSONObject json){
        JSONObject res = new JSONObject();
        String userid = (String) json.get("userid");
        Integer id = (Integer) json.get("songid");

        DeleteCollectModel curr = new DeleteCollectModel();
        curr.setUserid(userid);
        curr.setId(id);
        if(collectionService.delcollection(curr) > 0){
            redisService.srem(String.valueOf(id), "Collect", userid);
            res.put("code", 1);
        } else {
            res.put("code", 0);
        }
        return res;
    }
}
