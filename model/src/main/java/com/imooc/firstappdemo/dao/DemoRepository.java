package com.imooc.firstappdemo.dao;

import com.imooc.firstappdemo.domain.Demo;
import org.springframework.data.repository.CrudRepository;

/**
 * CrudRepository自带常用的crud方法
 * 这样一个基本的到就写完了
 *
 */
public interface DemoRepository extends CrudRepository<Demo,Long> {
}
