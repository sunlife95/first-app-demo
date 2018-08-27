package com.imooc.firstappdemo.framework.shiro.service;

import com.imooc.firstappdemo.framework.shiro.domain.UserInfo;

public interface UserInfoService {
    public UserInfo findByUserName(String username);
}
