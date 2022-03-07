package com.example.salieri;

import com.example.salieri.entity.CollectionKey;
import com.example.salieri.entity.Song;
import com.example.salieri.service.impl.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SalieriApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {
        CollectionKey collectionKey = new CollectionKey();
        collectionKey.setUserId("salieri");
        collectionKey.setId("1");
        collectionKey.setType(0);
        redisService.set(collectionKey, "collection_salieri_1_1");
        CollectionKey res = (CollectionKey) redisService.get("collection_salieri_1_1");
        System.out.println(res.getUserId());
        System.out.println(3*0.1 == 0.3);
    }
}
