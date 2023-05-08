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
    public int deleteByPrimaryKey(Integer songId) {
        return songMapper.deleteByPrimaryKey(songId);
    }

    @Override
    public List<Song> getsongBysongName(String name) {
        return songMapper.getsongBysongName(name);
    }

    @Override
    public List<Song> getsongById(List<Integer> id) {
        return songMapper.getsongByid(id);
    }

    @Override
    public Song getsongbyname(String name) {
        return songMapper.getsongByname(name);
    }

    @Override
    public List<Song> allsong() {
        return songMapper.allsong();
    }

    @Override
    public int insert(Song record) {
        return 0;
    }

    @Override
    public int insertSelective(Song record) {
        return songMapper.insertSelective(record);
    }

    @Override
    public Song selectByPrimaryKey(Integer songId) {
        return songMapper.selectByPrimaryKey(songId);
    }

    @Override
    public int updateByPrimaryKeySelective(Song record) {
        return songMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Song record) {
        return 0;
    }
}
