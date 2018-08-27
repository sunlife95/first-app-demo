package com.imooc.firstappdemo.framework.shiro.service.serviceImpl;

import com.imooc.firstappdemo.framework.shiro.domain.UserInfo;
import com.imooc.firstappdemo.framework.shiro.repository.UserInfoRepository;
import com.imooc.firstappdemo.framework.shiro.service.UserInfoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoServiceImpl implements UserInfoService{
    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    @Cacheable(value = "db1",key = "#username + 12138")
    public UserInfo findByUserName(String username) {
        System.out.println("UserInfoServiceImpl.findByUserName()");
        return userInfoRepository.findByUsername(username);
    }
}
