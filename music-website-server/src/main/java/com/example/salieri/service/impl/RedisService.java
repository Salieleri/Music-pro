package com.example.salieri.service.impl;

import com.example.salieri.API.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;
import java.util.Set;

@Service
public class RedisService {
    @Autowired
    private JedisPool jedisPool;

    public void set(Object value, String ...args){
        Jedis jedis = jedisPool.getResource();
        StringBuilder arg = new StringBuilder();
        Random rand = new Random();
        for (String s : args) {
            arg.append(s);
            arg.append('_');
        }
        Set<String> key = jedis.keys(String.valueOf(arg));
        if(key.isEmpty()){
            jedis.set(String.valueOf(arg).getBytes(), SerializeUtil.Serialize(value));
            jedis.expire(String.valueOf(arg).getBytes(), rand.nextInt(120));
        }
    }

    public Object get(String ...args){
        Jedis jedis = jedisPool.getResource();
        StringBuilder arg = new StringBuilder();
        for (String s : args) {
            arg.append(s);
            arg.append('_');
        }
        return SerializeUtil.UnSerialize(jedis.get(String.valueOf(arg).getBytes()));
    }
    public void update(String ...args){
        Jedis jedis = jedisPool.getResource();
        StringBuilder arg = new StringBuilder();
        for (String s : args) {
            arg.append(s);
            arg.append('_');
        }
        jedis.del(String.valueOf(arg));
    }
}
