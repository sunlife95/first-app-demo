package com.imooc.firstappdemo.framework.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
@Service
public class MyRedisSessionDao extends AbstractSessionDAO{

    //session超时时间，单位为毫秒
    private long expireTime = 120000;

    private RedisTemplate redisTemplate;//redis操作类

    public MyRedisSessionDao() {
        super();
    }
    public MyRedisSessionDao(long expireTime, RedisTemplate redisTemplate) {
        super();
        this.expireTime = expireTime;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public SessionIdGenerator getSessionIdGenerator() {
        return super.getSessionIdGenerator();
    }

    @Override
    public void setSessionIdGenerator(SessionIdGenerator sessionIdGenerator) {
        super.setSessionIdGenerator(sessionIdGenerator);
    }

    @Override
    protected Serializable generateSessionId(Session session) {
        return super.generateSessionId(session);
    }

    @Override
    public Serializable create(Session session) {
        return super.create(session);
    }

    @Override
    protected void assignSessionId(Session session, Serializable sessionId) {
        super.assignSessionId(session, sessionId);
    }

    @Override
    public Session readSession(Serializable sessionId) throws UnknownSessionException {
        return super.readSession(sessionId);
    }

    @Override
    protected Serializable doCreate(Session session) {
        System.out.println("=============doCreate=====================");
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session,sessionId);

        redisTemplate.opsForValue().set(session.getId(),session,expireTime,TimeUnit.MILLISECONDS);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        System.out.println("=========doReadSession============");
        if (serializable == null){
            return null;
        }
        return (Session)redisTemplate.opsForValue().get(serializable);
    }

    @Override   //更新session
    public void update(Session session) throws UnknownSessionException {
        System.out.println("===========update============");
        if (session == null ||session.getId() == null){
            return ;
        }
        session.setTimeout(expireTime);
        redisTemplate.opsForValue().set(session.getId(),session,expireTime, TimeUnit.MILLISECONDS);
    }

    @Override //删除session
    public void delete(Session session) {
        System.out.println("============delete================");
        if (null == session){
            return;
        }
        redisTemplate.opsForValue().getOperations().delete(session.getId());
    }

    @Override// 获取活跃的session，可以用来统计在线人数，如果要实现这个功能，
    // 可以在将session加入redis时指定一个session前缀，统计的时候则使用keys("session-prefix*")的方式来模糊
    // 查找redis中所有的session集合
    public Collection<Session> getActiveSessions() {
        System.out.println("==============getActiveSessions=================");
        return redisTemplate.keys("*");
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }
@Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }
}
