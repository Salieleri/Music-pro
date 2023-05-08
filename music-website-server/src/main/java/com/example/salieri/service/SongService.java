package com.example.salieri.service;

import com.example.salieri.entity.Song;

import java.util.List;

public interface SongService {
    int deleteByPrimaryKey(Integer songId);

    int insert(Song record);

    int insertSelective(Song record);

    Song selectByPrimaryKey(Integer songId);

    int updateByPrimaryKeySelective(Song record);

    int updateByPrimaryKey(Song record);

    List<Song> getsongBysongName(String name);

    List<Song> getsongById(List<Integer> id);

    List<Song> allsong();

    Song getsongbyname(String name);
}
