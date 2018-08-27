package com.imooc.firstappdemo.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 3)
public class MyStartupRunner3 implements ApplicationRunner {
    //ApplicationRunner接口run方法的参数时ApplicationArguments
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.err.println(">>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<333333");
    }
}
