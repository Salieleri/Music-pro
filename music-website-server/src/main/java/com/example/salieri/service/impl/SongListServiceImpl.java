package com.example.salieri.service.impl;

import com.example.salieri.dao.SongListMapper;
import com.example.salieri.entity.Song;
import com.example.salieri.entity.SongList;
import com.example.salieri.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    public int getnum() {
        return songListMapper.getnum();
    }

    @Override
    public List<Song> getsonginsonglist(String id) {
        List<String> songid = songListMapper.getsongid(id);
        return songListMapper.getsonginsonglist(songid);
    }

    @Override
    public int getnumofstyle(String style) {
        return songListMapper.getnumofstyle(style);
    }

    @Override
    public int deleteByPrimaryKey(String songlistId) {
        return 0;
    }


    @Override
    public List<SongList> getSongList(int maxdata, int page) {
        return songListMapper.getSongList(maxdata, page);
    }

    @Override
    public List<SongList> getsonglistofstyle(String style, int maxdata, int page) {
        return songListMapper.getsonglistofstyle(style, maxdata, page);
    }

    @Override
    public int insert(SongList record) {
        return 0;
    }

    @Override
    public int insertSelective(SongList record) {
        return 0;
    }

    @Override
    public SongList selectByPrimaryKey(String songlistId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(SongList record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SongList record) {
        return 0;
    }
}
