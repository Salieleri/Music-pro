package com.example.salieri.dao;

import com.example.salieri.entity.Song;
import com.example.salieri.entity.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongListMapper {
    int deleteByPrimaryKeyAndSongId(int songlistId, Integer songId);

    int insert(SongList record);

    int insertSelective(SongList record);

    SongList selectByPrimaryKey(Integer songlistId);

    SongList getlistbyname(String name);

    int updateByPrimaryKeySelective(SongList record);

    int updateByPrimaryKey(SongList record);

    int addsong(Integer songId, int songlistId);

    List<SongList> getSongList(int maxdata, int page);

    List<SongList> getsonglistofstyle(String style, int maxdata, int page);

    List<SongList> getlistbywords(String keywords);

    int getnum();

    int getnumofstyle(String style);

    List<String> getsongid(String id);

    List<Song> getsonginsonglist(List<String> songid);

    List<SongList> getallsonglist();

    int delList(int songlistId);

    int updateImg(SongList model);
}
