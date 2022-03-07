package com.example.salieri.service;

import com.example.salieri.entity.Comment;

import java.util.List;

public interface CommentService {
    public void insertComment(Comment comment);

    public void deleteComment(String userid, String id, String commentid);

    public List<Comment> getComment(String id, Integer type);

    public void updateComment(String commentid, Integer num);
}
