package com.example.salieri.dao;

import com.example.salieri.entity.Song;
import com.example.salieri.entity.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongListMapper {
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

    List<String> getsongid(String id);

    List<Song> getsonginsonglist(List<String> songid);
}
