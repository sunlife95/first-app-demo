package com.imooc.firstappdemo.config;

import net.sf.ehcache.Element;
import org.apache.shiro.dao.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.annotation.Annotation;
import java.util.concurrent.Callable;

import static jdk.nashorn.internal.runtime.JSType.toObject;
@Configuration
public class MyCache implements Cache {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String name;

    private net.sf.ehcache.Cache ehCache;

    private RedisTemplate<String, Object> redisTemplate;

    private long liveTime = 1*60*60; //默认1h=1*60*60

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @Nullable
    @Override
    public ValueWrapper get(Object key) {
        Element value = ehCache.get(key);
        logger.info("Cache L1 (ehcache) :{}={}",key,value);
        if (value==null){
            return (value != null ? new SimpleValueWrapper(value.getObjectValue()) : null);
        }
        //TODO 这样会不会更好？访问10次EhCache 强制访问一次redis 使得数据不失效
        final String keyStr = key.toString();
        Object objectValue = redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] key = keyStr.getBytes();
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                //每次获得，重置缓存过期时间
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return toObject(value);
            }
        },true);
        ehCache.put(new Element(key, objectValue));//取出来之后缓存到本地
        logger.info("Cache L2 (redis) :{}={}",key,objectValue);
        return  (objectValue != null ? new SimpleValueWrapper(objectValue) : null);
    }

    @Override
    public void put(Object key, @Nullable Object value) {
        ehCache.put(new Element(key, value));
        final String keyStr =  key.toString();
        final Object valueStr = value;
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] keyb = keyStr.getBytes();
                byte[] valueb = toByteArray(valueStr);
                connection.set(keyb, valueb);
                if (liveTime > 0) {
                    connection.expire(keyb, liveTime);
                }
                return 1L;
            }
        },true);
    }

    @Override
    public void evict(Object key) {
        ehCache.remove(key);
        final String keyStr =  key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.del(keyStr.getBytes());
            }
        },true);
    }

    @Override
    public void clear() {
        ehCache.removeAll();
        redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.flushDb();
                return "clear done.";
            }
        },true);
    }
    /**
     * 描述 : Object转byte[]. <br>
     * @param obj
     * @return
     */
    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    @Nullable
    @Override
    public <T> T get(Object key, @Nullable Class<T> type) {
        return null;
    }

    @Nullable
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Nullable
    @Override
    public ValueWrapper putIfAbsent(Object key, @Nullable Object value) {
        return null;
    }

    /**
     * 描述 :  byte[]转Object . <br>
     * @param bytes
     * @return
     */
    private Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    public void setName(String name) {
        this.name = name;
    }

    public net.sf.ehcache.Cache getEhCache() {
        return ehCache;
    }

    public void setEhCache(net.sf.ehcache.Cache ehCache) {
        this.ehCache = ehCache;
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(long liveTime) {
        this.liveTime = liveTime;
    }
}
