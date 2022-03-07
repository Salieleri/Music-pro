package com.example.salieri.service;

import com.example.salieri.entity.Song;
import com.example.salieri.entity.SongList;

import java.util.List;

public interface SongListService {
    int deleteByPrimaryKey(String songlistId);

    int insert(SongList record);

    int insertSelective(SongList record);

    SongList selectByPrimaryKey(String songlistId);

    int updateByPrimaryKeySelective(SongList record);

    int updateByPrimaryKey(SongList record);

    List<SongList> getSongList(int maxdata, int page);

    List<SongList> getsonglistofstyle(String style, int maxdata, int page);

    int getnum();

    int getnumofstyle(String style);

    List<Song> getsonginsonglist(String id);
}
