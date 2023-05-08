package com.example.salieri.entity;

import java.io.Serializable;

public class Song implements Serializable {
    private Integer songId;

    private String songName;

    private String songPos;

    private String songSinger;

    private String songPic;

    private String songIntro;

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
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

    public String getSongSinger() {
        return songSinger;
    }

    public void setSongSinger(String songSinger) {
        this.songSinger = songSinger == null ? null : songSinger.trim();
    }

    public String getSongPic() {
        return songPic;
    }

    public void setSongPic(String songPic) {
        this.songPic = songPic == null ? null : songPic.trim();
    }

    public String getSongIntro() {
        return songIntro;
    }

    public void setSongIntro(String songIntro) {
        this.songIntro = songIntro == null ? null : songIntro.trim();
    }
}
