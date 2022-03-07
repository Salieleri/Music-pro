package com.example.salieri.service.impl;

import com.example.salieri.dao.SongMapper;
import com.example.salieri.entity.Song;
import com.example.salieri.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    @Override
    public int deleteByPrimaryKey(String songId) {
        return 0;
    }

    @Override
    public List<Song> getsongBysongName(String name) {
        return songMapper.getsongBysongName(name);
    }

    @Override
    public Object allsong() {
        return songMapper.allsong();
    }

    @Override
    public int insert(Song record) {
        return 0;
    }

    @Override
    public int insertSelective(Song record) {
        return 0;
    }

    @Override
    public Song selectByPrimaryKey(String songId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Song record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Song record) {
        return 0;
    }
}
