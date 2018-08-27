package com.imooc.firstappdemo.controller.js;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("regex")
public class RegexController
{
    @RequestMapping("test")
    public String test(){
        return "jsRegex/HelloWorld";
    }
}
