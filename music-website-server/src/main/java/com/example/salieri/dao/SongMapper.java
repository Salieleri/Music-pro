package com.example.salieri.dao;

import com.example.salieri.entity.Song;

public interface SongMapper {
    int deleteByPrimaryKey(String songId);

    int insert(Song record);

    int insertSelective(Song record);

    Song selectByPrimaryKey(String songId);

    int updateByPrimaryKeySelective(Song record);

    int updateByPrimaryKey(Song record);
}