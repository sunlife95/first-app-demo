package com.imooc.firstappdemo.controller;

import com.alibaba.fastjson.JSON;
import com.imooc.firstappdemo.domain.Demo;
import com.imooc.firstappdemo.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/JPA")
public class JPAController {
    @Resource
    private DemoService demoService;

    @RequestMapping("/save")
    public String save(){
        Demo d = new Demo();
        d.setName("Angel");
        demoService.save(d);
        return "ok.JPAController.save";
    }
    @RequestMapping("/findAll")
    public String findAll(){
        return JSON.toJSONString(demoService.findAll(),false);
    }

    @RequestMapping("/findById")
    public Demo findById(long id){
        return demoService.getById(id);
    }
}
