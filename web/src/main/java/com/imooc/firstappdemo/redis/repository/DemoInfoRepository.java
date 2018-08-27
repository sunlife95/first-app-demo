package com.imooc.firstappdemo.redis.repository;

import com.imooc.firstappdemo.redis.domain.DemoInfo;
import org.springframework.data.repository.CrudRepository;
/**
 *@Author:Flm
 *@Description:持久化类
 *@Date:15:18 2018/3/8
 */
public interface DemoInfoRepository extends CrudRepository<DemoInfo,Long> {
}
