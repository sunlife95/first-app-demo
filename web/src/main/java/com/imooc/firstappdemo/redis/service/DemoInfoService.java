package com.imooc.firstappdemo.redis.service;

import com.imooc.firstappdemo.redis.domain.DemoInfo;
import org.springframework.stereotype.Service;
/**
 *@Author:Flm
 *@Description:service 接口
 *@Date:15:19 2018/3/8
 */
public interface DemoInfoService {
    public String findById(long id);

    public void deleteFromCache(long id);

    void test();
}
