package com.imooc.firstappdemo.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MyRedisConfigBean extends CachingConfigurerSupport{
    private StringRedisTemplate template;

    @Autowired
    public MyRedisConfigBean(StringRedisTemplate template){
        System.out.println("++++++++++++++++++"+template.getStringSerializer());
        this.template = template;
    }


}
