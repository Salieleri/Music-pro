package com.example.salieri.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.salieri.entity.Comment;
import com.example.salieri.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @ResponseBody
    @PostMapping("/comment/get")
    public List<Comment> getcomments(@RequestBody JSONObject json){
        Integer id = (Integer) json.get("id");
        Integer type = (Integer) json.get("type");
        return commentService.getComment(id, type);
    }

    @ResponseBody
    @PostMapping("/comment/post")
    public Object postcomment(@RequestBody JSONObject json){
        Integer id = (Integer) json.get("id");
        String content = (String) json.get("content");
        String userid = (String) json.get("userid");
        String createtime = (String) json.get("create_time");
        String avator = (String) json.get("avator");
        int like = (int) json.get("like");
        String commentid = (String) json.get("commentid");
        int type = (int) json.get("type");

        Comment comment = new Comment();
        comment.setArgs(id);
        comment.setComment_id(commentid);
        comment.setComment_content(content);
        comment.setComment_like(like);
        comment.setComment_type(type);
        comment.setComment_user_id(userid);
        comment.setComment_create_time(createtime);
        comment.setComment_user_avator(avator);

        commentService.insertComment(comment);

        JSONObject res = new JSONObject();

        res.put("code", 1);

        return res;
    }

    @ResponseBody
    @PostMapping("/comment/like")
    public Object postup(@RequestBody JSONObject json){
        JSONObject res = new JSONObject();

        String id = (String) json.get("id");
        int like = (int) json.get("num");

        res.put("code", 1);

        commentService.updateComment(id, like);

        return res;
    }
}
