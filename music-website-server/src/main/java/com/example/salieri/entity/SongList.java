package com.example.salieri.entity;

import java.io.Serializable;

public class SongList implements Serializable {
    private String songlistId;

    private String title;

    private String pic;

    private String intro;

    private String style;

    private String userId;

    public String getSonglistId() {
        return songlistId;
    }

    public void setSonglistId(String songlistId) {
        this.songlistId = songlistId == null ? null : songlistId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}
