package com.example.salieri.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@PropertySource(value = {"classpath:Redis/redis.properties"})
public class RedisConfig {
    @Value("${redis.node.maxTotal}")
    private int maxtotal;

    @Value("${redis.node.host}")
    private String host;

    @Value("${redis.node.port}")
    private int port;

    public JedisPoolConfig JedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxtotal);
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(){
        JedisPoolConfig jedisPoolConfig = JedisPoolConfig();
        return new JedisPool(jedisPoolConfig, host, port);
    }
}
