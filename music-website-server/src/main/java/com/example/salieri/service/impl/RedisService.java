package com.example.salieri.service.impl;

import com.example.salieri.API.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class RedisService {
    @Autowired
    private JedisPool jedisPool;

    public void set(Object value, String ...args){
        Jedis jedis = jedisPool.getResource();
        StringBuilder arg = new StringBuilder();
        for (String s : args) {
            arg.append(s);
            arg.append('_');
        }
        Set<String> key = jedis.keys(String.valueOf(arg));
        if (!key.isEmpty()) {
            return ;
        }
        jedis.set(String.valueOf(arg).getBytes(), SerializeUtil.Serialize(value));
        jedis.close();
    }

    public Object get(String ...args){
        Jedis jedis = jedisPool.getResource();
        StringBuilder arg = new StringBuilder();
        for (String s : args) {
            arg.append(s);
            arg.append('_');
        }
        byte[] res = jedis.get(String.valueOf(arg).getBytes());
        jedis.close();
        return res != null ? SerializeUtil.UnSerialize(res) : null;
    }
    public void update(String ...args){
        Jedis jedis = jedisPool.getResource();
        StringBuilder arg = new StringBuilder();
        for (String s : args) {
            arg.append(s);
            arg.append('_');
        }
        jedis.del(String.valueOf(arg).getBytes());
        jedis.close();
    }
    public void setnx(String ...args){
        Random rand = new Random();
        Jedis jedis = jedisPool.getResource();
        StringBuilder arg = new StringBuilder();
        for (String s : args) {
            arg.append(s);
            arg.append('_');
        }
        jedis.expire(String.valueOf(arg).getBytes(), rand.nextInt(120) + 60);
        jedis.close();
    }
    public Set<String> keys(String pat){
        Jedis jedis = jedisPool.getResource();
        pat += '_';
        jedis.close();
        return jedis.keys(pat + '*');
    }

    public List<Object> mget(Set<String> keys){
        Jedis jedis = jedisPool.getResource();

        List<byte[]> end = new ArrayList<>();
        for(String i: keys){
            end.add(jedis.get(i.getBytes()));
        }

        List<Object> res = new ArrayList<>();
        for(byte[] i: end){
            res.add(SerializeUtil.UnSerialize(i));
        }
        jedis.close();
        return res;
    }
    public List<Object> mgetWithoutSerial(Set<String> keys){
        Jedis jedis = jedisPool.getResource();
        List<Object> res = new ArrayList<>();
        for(String i: keys){
            res.add(jedis.get(i));
        }
        return res;
    }

    public void sadd(String value ,String ...args){
        Jedis jedis = jedisPool.getResource();
        StringBuilder arg = new StringBuilder();
        for (String s : args) {
            arg.append(s);
            arg.append('_');
        }
        jedis.sadd(String.valueOf(arg),value);
        jedis.close();
    }

    public Set<String> smembers(String ...args){
        Jedis jedis = jedisPool.getResource();
        StringBuilder arg = new StringBuilder();
        for (String s : args) {
            arg.append(s);
            arg.append('_');
        }
        Set<String> res = jedis.smembers(String.valueOf(arg));
        jedis.close();
        return res;
    }

    public void srem(String value ,String ...args){
        Jedis jedis = jedisPool.getResource();
        StringBuilder arg = new StringBuilder();
        for (String s : args) {
            arg.append(s);
            arg.append('_');
        }
        jedis.srem(String.valueOf(arg), value);
        jedis.close();
    }

    public boolean sismember(String value ,String ...args){
        Jedis jedis = jedisPool.getResource();
        StringBuilder arg = new StringBuilder();
        for (String s : args) {
            arg.append(s);
            arg.append('_');
        }
        boolean confirm = jedis.sismember(String.valueOf(arg), value);
        jedis.close();
        return confirm;
    }
}
