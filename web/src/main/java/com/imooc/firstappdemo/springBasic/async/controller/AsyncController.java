package com.imooc.firstappdemo.springBasic.async.controller;

import com.imooc.firstappdemo.springBasic.async.domain.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Resource
    private Task task;

    @RequestMapping("/test")
    public String testAsync() throws InterruptedException {
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
        return "ok";
    }
}
