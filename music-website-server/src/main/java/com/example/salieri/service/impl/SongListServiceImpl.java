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
    public int addsong(Integer songId, int songlistId) {
        return songListMapper.addsong(songId, songlistId);
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
    public SongList getlistbyname(String name) {
        return songListMapper.getlistbyname(name);
    }

    @Override
    public int deleteByPrimaryKeyAndSongId(Integer songlistId, Integer songId) {
        return songListMapper.deleteByPrimaryKeyAndSongId(songlistId, songId);
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
    public List<SongList> getlistbywords(String keywords) {
        return songListMapper.getlistbywords(keywords);
    }

    @Override
    public int delList(int songlistId) {
        return songListMapper.delList(songlistId);
    }

    @Override
    public int insertSelective(SongList record) {
        return songListMapper.insertSelective(record);
    }

    @Override
    public SongList selectByPrimaryKey(Integer songlistId) {
        return songListMapper.selectByPrimaryKey(songlistId);
    }

    @Override
    public int updateByPrimaryKeySelective(SongList record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(SongList record) {
        return 0;
    }

    @Override
    public List<SongList> getallsonglist() {
        return songListMapper.getallsonglist();
    }

    public int update(SongList model) {
        return songListMapper.updateByPrimaryKeySelective(model);
    }
}
