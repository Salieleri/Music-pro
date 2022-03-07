package com.example.salieri.service.impl;

import com.example.salieri.dao.CommentRepository;
import com.example.salieri.entity.Comment;
import com.example.salieri.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void insertComment(Comment comment) {
        commentRepository.insertComment(comment);
    }

    @Override
    public void deleteComment(String userid, String id,String commentid) {
        commentRepository.deleteComment(userid, id, commentid);
    }

    @Override
    public List<Comment> getComment(String id, Integer type) {
        return commentRepository.getComment(id, type);
    }

    @Override
    public void updateComment(String commentid, Integer num) {
        commentRepository.updateComment(commentid, num);
    }
}
