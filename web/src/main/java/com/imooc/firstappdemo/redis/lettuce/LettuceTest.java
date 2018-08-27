package com.imooc.firstappdemo.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import redis.clients.jedis.Jedis;

public class LettuceTest {
    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://@127.0.0.1:6379/0");
        StatefulRedisConnection<String,String> connection = redisClient.connect();
        RedisCommands<String,String> Commands = connection.sync();//异步操作
        Commands.set("key", "Hello, Redis!");
        System.out.println(Commands.get("key"));
        connection.close();
        redisClient.shutdown();
    }
}
