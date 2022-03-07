package com.example.salieri.service;

import com.example.salieri.entity.Song;

import java.util.List;

public interface SongService {
    int deleteByPrimaryKey(String songId);

    int insert(Song record);

    int insertSelective(Song record);

    Song selectByPrimaryKey(String songId);

    int updateByPrimaryKeySelective(Song record);

    int updateByPrimaryKey(Song record);

    List<Song> getsongBysongName(String name);

    Object allsong();
}
