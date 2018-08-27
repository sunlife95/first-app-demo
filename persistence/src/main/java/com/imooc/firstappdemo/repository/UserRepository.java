package com.imooc.firstappdemo.repository;

import com.imooc.firstappdemo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link User} {@link Repository}
 */
@Repository
public class UserRepository {

    /**
     * 采用内存型的存储方式 ->Map
     */
    private ConcurrentMap<Integer,User> repository = new ConcurrentHashMap<>();

    private final static AtomicInteger idGenerater = new AtomicInteger();
    /**
     * 保存用户对象
     * @param user
     * @return 如果成功，返回<code>true</code>
     *          否则，返回<code>false</code>
     */
    public boolean save(User user){
            boolean success = false ;
            //ID 从1开始
            Integer id = idGenerater.incrementAndGet();
            user.setId(id);
            return repository.put(id,user) == null;
    }
    /**
     * 返回用户列表
     */
    public Collection<User> findAll(){
        return repository.values();
    }
}
