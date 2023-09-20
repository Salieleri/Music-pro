package com.example.salieri.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.salieri.entity.Song;
import com.example.salieri.entity.SongList;
import com.example.salieri.service.impl.RedisService;
import com.example.salieri.service.impl.SongListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.example.salieri.constant.constant.RESOURCE_PATH_MAC;
import static com.example.salieri.constant.constant.RESOURCE_PATH_WIN;

@Controller
public class SongListController {
    @Autowired
    private SongListServiceImpl songListService;

    @Autowired
    private RedisService redisService;

    // 按页获取歌单
    @ResponseBody
    @PostMapping("/songlist/get")
    public List<SongList> getsonglist(@RequestBody JSONObject json){
        int maxdata = (int) json.get("maxdata");
        int page = (int) json.get("page");
        return songListService.getSongList(maxdata, page * maxdata);
    }

    // 搜索歌单
    @ResponseBody
    @GetMapping("/songlist/getlistbykeywords")
    public List<SongList> getlistbywords(HttpServletRequest req){
        String keywords = req.getParameter("keywords");
        return songListService.getlistbywords(keywords);
    }

    // 按风格获取歌单
    @ResponseBody
    @PostMapping("/songlist/style/get")
    public List<SongList> getsonglistofstyle(@RequestBody JSONObject json){
        String style = (String) json.get("style");
        int maxdata = (int) json.get("maxdata");
        int page = (int) json.get("page");
        return songListService.getsonglistofstyle(style, maxdata, page);
    }

    // 取得歌单数
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

    // 获取歌单内歌曲
    @ResponseBody
    @GetMapping("/songlist/getsong")
    public List<Song> getsonginsonglist(HttpServletRequest req){
        String id = req.getParameter("id");
        return songListService.getsonginsonglist(id);
    }

    // 获得所有歌单
    @ResponseBody
    @GetMapping("/songlist")
    public List<SongList> getallsonglist(){
        return songListService.getallsonglist();
    }

    // 添加歌单内容
    @ResponseBody
    @PostMapping("/songlist/add")
    public Object addsong(@RequestBody JSONObject json){
        Integer songId = (Integer) json.get("songId");
        int songlistId = (int) json.get("songlistId");
        JSONObject res = new JSONObject();
        int result = songListService.addsong(songId, songlistId);
        if(result == 1){
            redisService.sadd(String.valueOf(songId), "ListSong", String.valueOf(songlistId));
            res.put("code", 1);
        } else {
            res.put("code", 0);
        }
        return res;
    }

    // 删除歌单内容
    @ResponseBody
    @PostMapping("/songlist/delete")
    public Object delsong(@RequestBody JSONObject json){
        Integer songId = (Integer) json.get("songId");
        Integer songlistId = (Integer) json.get("songlistId");
        JSONObject res = new JSONObject();
        int result = songListService.deleteByPrimaryKeyAndSongId(songlistId, songId);
        if(result == 1){
            redisService.srem(String.valueOf(songId), "ListSong", String.valueOf(songlistId));
            res.put("code", 1);
        } else {
            res.put("code", 0);
        }
        return res;
    }

    // 删除歌单
    @ResponseBody
    @PostMapping("/songlist/delList")
    public Object delList(@RequestBody JSONObject req){
        Integer id = (Integer) req.get("id");
        String img = (String) req.get("img");

        File Img = null;
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            Img = new File(RESOURCE_PATH_WIN + img);
        } else {
            Img = new File(RESOURCE_PATH_MAC + img);
        }

        if(Img.exists()){
            Img.delete();
        }

        JSONObject res = new JSONObject();

        int result = songListService.delList(id);

        if(result == 1){
            redisService.update("SongList", String.valueOf(id));
            res.put("code", 1);
        } else {
            res.put("code", 0);
        }
        return res;
    }

    // 添加歌单
    @ResponseBody
    @PostMapping("/songlist/addlist")
    public Object addlist(@RequestBody JSONObject json){
        String title = (String) json.get("title");
        String intro = (String) json.get("intro");
        String style = (String) json.get("style");

        SongList list = new SongList();
        list.setIntro(intro);
        list.setStyle(style);
        list.setTitle(title);

        int result = songListService.insertSelective(list);

        JSONObject res = new JSONObject();
        if(result == 1){
            SongList ins = songListService.getlistbyname(title);
            redisService.set(ins, "SongList", String.valueOf(ins.getSonglistId()));
            res.put("code", 1);
        } else {
            res.put("code", 0);
        }
        return res;
    }

    // 更新歌单图片
    @ResponseBody
    @PostMapping("/songlist/img/update")
    public Object UpdateAvator(@RequestParam("file") MultipartFile songlistimg,
                               @RequestParam("id") String id,
                               @RequestParam("past") String past){
        JSONObject res = new JSONObject();

        int Id = Integer.parseInt(id);
        if(songlistimg.isEmpty()){
            res.put("code", 0);
            res.put("mes", "文件为空!");
            return res;
        }

        String fileName = System.currentTimeMillis() + id + ".jpg";
        String filePath = "";
        String storePath = "/img/SongList/" + fileName;

        File fileofpast = null;
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            filePath = RESOURCE_PATH_WIN + "\\img\\SongList\\";
            fileofpast = new File(RESOURCE_PATH_WIN + System.getProperty("file.separator") + past);
        } else {
            filePath = RESOURCE_PATH_MAC + "/img/SongList/";
            fileofpast = new File(RESOURCE_PATH_MAC + System.getProperty("file.separator") + past);
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        if(fileofpast.exists()){
            fileofpast.delete();
        }
        try {
            songlistimg.transferTo(dest);
            SongList model = new SongList();
            model.setSonglistId(Id);
            model.setPic(storePath);
            int tot = songListService.update(model);
            if(tot != 0) {
                SongList list = songListService.selectByPrimaryKey(Id);
                redisService.update("SongList", String.valueOf(Id));
                list.setPic(storePath);
                redisService.set(list, "SongList", String.valueOf(Id));
                res.put("code", 1);
                res.put("mes", "上传成功");
                res.put("path", storePath);
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
    @PostMapping("/songlist/update")
    public Object UpdateSongList(@RequestBody JSONObject json){
        int songlistid = (int) json.get("id");
        String title = (String) json.get("title");
        String style = (String) json.get("style");
        String intro = (String) json.get("intro");

        SongList list = new SongList();
        list.setSonglistId(songlistid);
        list.setTitle(title);
        list.setStyle(style);
        list.setIntro(intro);

        int result = songListService.update(list);

        JSONObject res = new JSONObject();
        if(result == 1){
            redisService.update("SongList", String.valueOf(songlistid));
            redisService.set(list, "SongList", String.valueOf(songlistid));
            res.put("code", 1);
        } else {
            res.put("code", 0);
        }
        return res;
    }
}
