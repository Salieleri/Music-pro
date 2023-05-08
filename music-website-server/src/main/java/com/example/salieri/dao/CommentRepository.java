package com.example.salieri.dao;

import com.example.salieri.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    //提交评论
    public void insertComment(Comment comment){
        mongoTemplate.insert(comment, "comment");
    }

    public void deleteComment(String userid, String id, String commentid){
        Query query = new Query(Criteria.where("comment_user_id").is(userid).and("id").is(id).and("comment_id").is(commentid));
        mongoTemplate.remove(query);
    }

    public List<Comment> getComment(int id, int type){
        Query query = new Query();
        Criteria criteria = Criteria.where("args").is(id).and("comment_type").is(type);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Comment.class, "comment");
    }

    //点赞
    public void updateComment(String commentid, Integer num){
        Query query = new Query(Criteria.where("comment_id").is(commentid));
        Update update = Update.update("comment_like", num);
        mongoTemplate.updateFirst(query, update, Comment.class);
    }
}
