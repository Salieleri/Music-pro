package com.example.salieri.dao;

import com.example.salieri.entity.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongMapper {
    int deleteByPrimaryKey(String songId);

    int insert(Song record);

    int insertSelective(Song record);

    Song selectByPrimaryKey(String songId);

    int updateByPrimaryKeySelective(Song record);

    int updateByPrimaryKey(Song record);

    List<Song> getsongBysongName(String name);

    List<Song> getsongByid(List<String> collectid);

    Object allsong();
}
