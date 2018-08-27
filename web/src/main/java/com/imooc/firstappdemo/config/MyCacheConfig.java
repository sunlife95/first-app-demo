package com.imooc.firstappdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
@Configuration
public class MyCacheConfig {
    @Bean
    public MyCache myCache(){
        MyCache myCache = new MyCache();
        return myCache;
    }
    @Bean
    public SimpleCacheManager simpleCacheManager(MyCache myCache){
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        ArrayList<Cache> arrayList = new ArrayList<Cache>();
        arrayList.add(myCache);
        simpleCacheManager.setCaches(arrayList);
        return simpleCacheManager;
    }
}
