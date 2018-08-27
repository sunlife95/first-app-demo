package com.imooc.firstappdemo.springBasic.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aop")
public class aopController {
    @RequestMapping("/hello")
    public String hello(String name,String age){
        return "ok";
    }
}
