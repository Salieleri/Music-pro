package com.example.salieri.dao;

import com.example.salieri.entity.CollectionKey;
import com.example.salieri.entity.model.DeleteCollectModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionMapper {
    int deleteByPrimaryKey(CollectionKey key);

    int insert(CollectionKey record);

    int insertSelective(CollectionKey record);

    Optional<CollectionKey> getonecollect(String userid, String id);

    List<String> getcollectid(String id);

    int delcollection(DeleteCollectModel model);
}
