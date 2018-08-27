package com.imooc.firstappdemo.redis.service;

import com.imooc.firstappdemo.domain.Demo;
import com.imooc.firstappdemo.redis.domain.DemoInfo;
import com.imooc.firstappdemo.redis.repository.DemoInfoRepository;
import com.imooc.firstappdemo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.util.Optional;

@Service
public class DemoInfoServiceImpl implements DemoInfoService{
    @Resource
    private DemoInfoRepository demoInfoRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    @Cacheable(value = "db1",key = "#id")
    public String findById(long id) {
        System.out.println("DemoInfoServiceImpl.findById()=========从数据库中进行获取的....id="+id);
        Optional<DemoInfo> o = demoInfoRepository.findById(id);
        return o.get().toString();
//        return  stringRedisTemplate.opsForValue().get("mykey4");
    }

    @CacheEvict(value = "demoInfo")
    @Override
    public void deleteFromCache(long id) {
        System.out.println("DemoInfoServiceImpl.delete().从缓存中删除.");
    }

    @Override
    public void test() {
        DemoInfo s = new DemoInfo();
        s.setId(1);
        s.setName("孙令福");
        s.setPwd("123456");
        demoInfoRepository.save(s);
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("mykey4","random1="+Math.random());
        System.out.println(valueOperations.get("mykey4"));
    }
}
