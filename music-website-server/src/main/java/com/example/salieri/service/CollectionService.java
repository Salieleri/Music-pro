package com.example.salieri.service;

import com.example.salieri.entity.CollectionKey;
import com.example.salieri.entity.Song;
import com.example.salieri.entity.model.DeleteCollectModel;

import java.util.List;

public interface CollectionService {
    boolean confirm(String userid, String id);

    List<Song> getcollection(String userid);

    int addcollection(CollectionKey curr);

    int delcollection(DeleteCollectModel model);
}
