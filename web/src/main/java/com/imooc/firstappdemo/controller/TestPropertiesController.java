package com.imooc.firstappdemo.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.firstappdemo.propertiesBean.MySettings;
import com.imooc.firstappdemo.propertiesBean.MySettings2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestPropertiesController {
    @Resource
    MySettings mySettings;
    @Resource
    MySettings2 mySettings2;
    @RequestMapping("/testProperties")
    public String test(){
        System.out.println(mySettings.toString());
        System.out.println(mySettings2.toString());
        return "ok";
    }
}
