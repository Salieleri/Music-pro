package com.example.salieri.service.impl;

import com.example.salieri.dao.CollectionMapper;
import com.example.salieri.dao.SongMapper;
import com.example.salieri.entity.CollectionKey;
import com.example.salieri.entity.Song;
import com.example.salieri.entity.model.DeleteCollectModel;
import com.example.salieri.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Autowired
    private SongMapper songMapper;

    @Override
    public int addcollection(CollectionKey curr) {
        return collectionMapper.insert(curr);
    }

    @Override
    public int delcollection(DeleteCollectModel model) {
        return collectionMapper.delcollection(model);
    }

    @Override
    public List<Song> getcollection(String userid) {
        List<String> collectid = collectionMapper.getcollectid(userid);
        if(collectid.isEmpty())return null;
        return songMapper.getsongByid(collectid);
    }

    @Override
    public boolean confirm(String userid, String id) {
        Optional<CollectionKey> isexist = collectionMapper.getonecollect(userid, id);
        return isexist.isPresent();
    }
}
