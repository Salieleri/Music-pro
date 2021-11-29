package com.example.salieri.entity;

public class Song {
    private String songId;

    private String songName;

    private String songPos;

    private Integer songPraise;

    private Integer songCount;

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId == null ? null : songId.trim();
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName == null ? null : songName.trim();
    }

    public String getSongPos() {
        return songPos;
    }

    public void setSongPos(String songPos) {
        this.songPos = songPos == null ? null : songPos.trim();
    }

    public Integer getSongPraise() {
        return songPraise;
    }

    public void setSongPraise(Integer songPraise) {
        this.songPraise = songPraise;
    }

    public Integer getSongCount() {
        return songCount;
    }

    public void setSongCount(Integer songCount) {
        this.songCount = songCount;
    }
}