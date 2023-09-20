package com.example.salieri.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.salieri.entity.Song;
import com.example.salieri.service.impl.RedisService;
import com.example.salieri.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.example.salieri.constant.constant.RESOURCE_PATH_MAC;
import static com.example.salieri.constant.constant.RESOURCE_PATH_WIN;

@RestController
@Controller
public class SongController {
    @Autowired
    private SongServiceImpl SongService;

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @GetMapping("/Song/getbyname")
    public List<Song> getsongBysongName(HttpServletRequest req){
        String name = req.getParameter("name");
        return SongService.getsongBysongName(name);
    }

    @ResponseBody
    @GetMapping("/Song/getsongbyname")
    public Song getsongbyname(HttpServletRequest req){
        String name = req.getParameter("name");
        Set<String> keys = redisService.keys("Song");
        List<Object> data = redisService.mget(keys);
        for(Object i: data){
            if(((Song)i).getSongName().equals(name))return (Song) i;
        }
        Song ins = SongService.getsongbyname(name);
        if(ins != null) redisService.set(ins, "Song", String.valueOf(ins.getSongId()));
        return ins;
    }

//    获得所有歌曲
    @GetMapping("/Song")
    public List<Object> getallsongs(){
        Set<String> set = redisService.keys("Song");
        List<Object> res = redisService.mget(set);
        if(res.isEmpty()){
            res = Collections.singletonList(SongService.allsong());
        }
        return res;
    }

    @ResponseBody
    @PostMapping("/Song/img/update")
    public Object UpdateSongImg(@RequestParam("file") MultipartFile songImg,
                                @RequestParam("id") String id,
                                @RequestParam("past") String past){
        JSONObject res = new JSONObject();

        if(songImg.isEmpty()){
            res.put("code",0);
            res.put("mes", "文件为空!");
            return res;
        }

        String fileName = System.currentTimeMillis() + id + ".jpg";
        String filePath = "";
        String storePath = "/img/SongPic/" + fileName;

        File fileofpast = null;
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            filePath = RESOURCE_PATH_WIN + "\\img\\SongPic\\";
            fileofpast = new File(RESOURCE_PATH_WIN + System.getProperty("file.separator") + past);
        } else {
            filePath = RESOURCE_PATH_MAC + "/img/SongPic/";
            fileofpast = new File(RESOURCE_PATH_MAC + System.getProperty("file.separator") + past);
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        if(fileofpast.exists()){
            fileofpast.delete();
        }

        try{
            songImg.transferTo(dest);
            Song update = new Song();
            update.setSongId(Integer.valueOf(id));
            update.setSongPic(storePath);

            int tot = SongService.updateByPrimaryKeySelective(update);

            if(tot != 0){
                Song data = SongService.selectByPrimaryKey(Integer.valueOf(id));
                data.setSongPic(storePath);
                redisService.update("Song", id);
                redisService.set(data, "Song", id);
                res.put("code", 1);
                res.put("mes", "更新成功!");
            } else {
                res.put("code", 0);
                res.put("mes", "更新失败!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    @ResponseBody
    @PostMapping("/Song/url/update")
    public Object UpdateSongUrl(@RequestParam("file") MultipartFile songUrl,
                                @RequestParam("songId")String songid,
                                @RequestParam("id")String id,
                                @RequestParam("singer")String singer,
                                @RequestParam("past") String past){
        JSONObject res = new JSONObject();

        if(songUrl.isEmpty()){
            res.put("code",0);
            res.put("mes", "文件为空!");
            return res;
        }

        String fileName = singer + " - " + id + ".mp3";
        String filePath = "";
        String storePath = "/Song/" + fileName;

        File fileofpast = null;
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            filePath = RESOURCE_PATH_WIN + "\\Song\\";
            fileofpast = new File(RESOURCE_PATH_WIN + System.getProperty("file.separator") + past);
        } else {
            filePath = RESOURCE_PATH_MAC + "/Song/";
            fileofpast = new File(RESOURCE_PATH_MAC + System.getProperty("file.separator") + past);
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        if(fileofpast.exists()){
            fileofpast.delete();
        }

        try{
            songUrl.transferTo(dest);
            Song update = new Song();
            update.setSongId(Integer.valueOf(songid));
            update.setSongName(id);
            update.setSongSinger(singer);
            update.setSongPos(storePath);

            int tot = SongService.updateByPrimaryKeySelective(update);

            if(tot != 0){
                Song data = SongService.selectByPrimaryKey(Integer.valueOf(songid));
                data.setSongPos(storePath);
                redisService.update("Song", songid);
                redisService.set(update, "Song", songid);
                res.put("code", 1);
                res.put("mes", "更新成功!");
            } else {
                res.put("code", 0);
                res.put("mes", "更新失败!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    @ResponseBody
    @PostMapping("/Song/add")
    public Object AddSong(@RequestBody JSONObject json){
        String name = (String) json.get("name");
        String singer = (String) json.get("singer");
        String intro = (String) json.get("intro");

        Song preins = new Song();

        preins.setSongName(name);
        preins.setSongSinger(singer);
        preins.setSongIntro(intro);

        JSONObject res = new JSONObject();

        int result = SongService.insertSelective(preins);

        if(result != 0){
            Song ins = SongService.getsongbyname(name);
            redisService.set(ins, "Song", String.valueOf(ins.getSongId()));
            res.put("code", 1);
        } else {
            res.put("code", 0);
        }
        return res;
    }

    @ResponseBody
    @PostMapping("/Song/update")
    public Object UpdateSong(@RequestBody JSONObject json){
        int songId = (int) json.get("id");
        String singer = (String) json.get("singer");
        String name = (String) json.get("name");
        String intro = (String) json.get("intro");

        Song preupd = new Song();

        preupd.setSongSinger(singer);
        preupd.setSongId(songId);
        preupd.setSongName(name);
        preupd.setSongIntro(intro);

        int result = SongService.updateByPrimaryKeySelective(preupd);

        JSONObject res = new JSONObject();

        if(result != 0){
            Song ins = SongService.getsongbyname(name);
            redisService.update("Song", String.valueOf(songId));
            redisService.set(ins, "Song", String.valueOf(songId));
            res.put("code", 1);
        } else {
            res.put("code", 0);
        }
        return res;
    }

    @ResponseBody
    @PostMapping("/Song/delete")
    public Object DelSong(@RequestBody JSONObject json){
        int songId = (int) json.get("id");
        String url = (String) json.get("url");
        String img = (String) json.get("img");

        int result = SongService.deleteByPrimaryKey(songId);

        File Img = null;
        File Url = null;
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            Img = new File(RESOURCE_PATH_WIN + img);
            Url = new File(RESOURCE_PATH_WIN + url);
        } else {
            Img = new File(RESOURCE_PATH_MAC + img);
            Url = new File(RESOURCE_PATH_MAC + url);
        }

        if(Img.exists()){
            Img.delete();
        }

        if(Url.exists()){
            Url.delete();
        }

        JSONObject res = new JSONObject();
        if(result != 0){
            redisService.update("Song", String.valueOf(songId));
            res.put("code",1);
        } else {
            res.put("code",0);
        }
        return res;
    }

    @ResponseBody
    @GetMapping("/Song/download")
    public void download(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String url = req.getParameter("url");
        String filename = req.getParameter("name");
        String[] mid = url.split("4372");
        String filepath = mid[1];

        filename = URLEncoder.encode(filename, "UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Pragma", "No-Cache");
        response.setHeader("Cache-Control", "No-Cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("application/x-download; charset=UTF-8");
        response.setHeader("Content-disposition","attachment;filename=" + filename);

        InputStream in = null;
        ServletOutputStream out = null;
        byte[] buff = new byte[1024];
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            in = Files.newInputStream(Paths.get(RESOURCE_PATH_WIN + filepath));
        } else {
            in = Files.newInputStream(Paths.get(RESOURCE_PATH_MAC + filepath));
        }
        out = response.getOutputStream();
        int aRead = 0;
        int kilobyte = 0;
        while ((aRead = in.read(buff)) > 0) {
            out.write(buff,0,aRead);
            kilobyte++;
        }
        System.out.println(kilobyte);
        out.flush();
        in.close();
        out.close();
        out.flush();
    }
}
