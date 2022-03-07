package com.example.salieri.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.salieri.entity.Song;
import com.example.salieri.entity.SongList;
import com.example.salieri.service.impl.SongListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SongListController {
    @Autowired
    SongListServiceImpl songListService;

    @ResponseBody
    @PostMapping("/songlist/get")
    public List<SongList> getsonglist(@RequestBody JSONObject json){
        int maxdata = (int) json.get("maxdata");
        int page = (int) json.get("page");
        return songListService.getSongList(maxdata, page * maxdata);
    }

    @ResponseBody
    @PostMapping("/songlist/style/get")
    public List<SongList> getsonglistofstyle(@RequestBody JSONObject json){
        String style = (String) json.get("style");
        int maxdata = (int) json.get("maxdata");
        int page = (int) json.get("page");
        return songListService.getsonglistofstyle(style, maxdata, page);
    }

    @ResponseBody
    @GetMapping("/songlist/nums")
    public int getnumsforsonglist(HttpServletRequest req){
        String style = req.getParameter("style");
        if(style.equals("全部歌单")){
            return songListService.getnum();
        }
        else {
            return songListService.getnumofstyle(style);
        }
    }

    @ResponseBody
    @GetMapping("/songlist/getsong")
    public List<Song> getsonginsonglist(HttpServletRequest req){
        String id = req.getParameter("id");
        return songListService.getsonginsonglist(id);
    }
}
