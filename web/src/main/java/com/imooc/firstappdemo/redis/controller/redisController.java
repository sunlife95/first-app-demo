package com.imooc.firstappdemo.redis.controller;

import com.imooc.firstappdemo.redis.domain.DemoInfo;
import com.imooc.firstappdemo.redis.service.DemoInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 *@Author:Flm
 *@Description:redis测试controller
 *@Date:15:28 2018/3/8
 */
@Controller
@RequestMapping("/redis")
public class redisController {
    @Resource
    private DemoInfoService demoInfoService;

    @RequestMapping("/test")
    public @ResponseBody String test(){
        String loaded = demoInfoService.findById(10);
        System.out.println("loaded="+loaded);
        return "ok";
    }

    @RequestMapping("/delete")
    public @ResponseBody String delete(long id){
        demoInfoService.deleteFromCache(id);
        return "ok";
    }

    @RequestMapping("test1")
    public @ResponseBody String test1(){
        demoInfoService.test();
        System.out.println("DemoInfoController.test1()");
        return "ok";
    }

}
