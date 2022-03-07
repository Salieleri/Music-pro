package com.example.salieri.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.salieri.entity.CollectionKey;
import com.example.salieri.entity.Song;
import com.example.salieri.entity.model.DeleteCollectModel;
import com.example.salieri.service.impl.CollectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CollectionController {

    @Autowired
    private CollectionServiceImpl collectionService;

    @ResponseBody
    @PostMapping("/collect/confirm")
    public Object confirm(@RequestBody JSONObject req){
        JSONObject json = new JSONObject();
        String userid = (String) req.get("userid");
        String songid = (String) req.get("id");
        boolean isexist = collectionService.confirm(userid, songid);
        if(isexist){
            json.put("code", 1);
        } else {
          json.put("code", 0);
        }
        return json;
    }

    @ResponseBody
    @GetMapping("/collect/get")
    public List<Song> getcollection(HttpServletRequest req){
        String id = req.getParameter("id");
        return collectionService.getcollection(id);
    }

    @ResponseBody
    @PostMapping("/collect/add")
    public Object setcollection(@RequestBody JSONObject json){
        JSONObject res = new JSONObject();
        String userid = (String) json.get("userid");
        String id = (String) json.get("songid");
        Integer type = (Integer) json.get("type");

        CollectionKey curr = new CollectionKey();
        curr.setUserId(userid);
        curr.setId(id);
        curr.setType(type);

        if(collectionService.addcollection(curr) > 0){
            res.put("code", 1);
        } else {
            res.put("code", 0);
        }
        return res;
    }

    @ResponseBody
    @PostMapping("/collect/del")
    public Object delcollection(@RequestBody JSONObject json){
        JSONObject res = new JSONObject();
        String userid = (String) json.get("userid");
        String id = (String) json.get("songid");

        DeleteCollectModel curr = new DeleteCollectModel();
        curr.setUserid(userid);
        curr.setId(id);
        if(collectionService.delcollection(curr) > 0){
            res.put("code", 1);
        } else {
            res.put("code", 0);
        }
        return res;
    }
}
