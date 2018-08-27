package com.imooc.firstappdemo.framework.shiro.repository;

import com.imooc.firstappdemo.framework.shiro.domain.UserInfo;
import org.springframework.data.repository.CrudRepository;
/**
 *@Author:Flm
 *@Description:UserInfo持久化类
 *@Date:17:14 2018/3/13
 */
public interface UserInfoRepository extends CrudRepository<UserInfo,Long> {
    //通过username查找用户信息
    public UserInfo findByUsername(String username);
}
