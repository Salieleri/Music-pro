package com.example.salieri.controller;

import com.example.salieri.entity.Song;
import com.example.salieri.service.impl.SongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Controller
public class SongController {
    @Autowired
    private SongServiceImpl SongService;

    @ResponseBody
    @GetMapping("/Song/getbyname")
    public List<Song> getsongBysongName(HttpServletRequest req){
        String name = req.getParameter("name");
        return SongService.getsongBysongName(name);
    }

//    获得所有歌曲
    @GetMapping("/Song")
    public Object getallsongs(){
        return SongService.allsong();
    }
}
