package com.imooc.firstappdemo.springBasic.createBeanByJavaCode.Controller;

import com.imooc.firstappdemo.springBasic.createBeanByJavaCode.impl.ShanhyA;
import com.imooc.firstappdemo.springBasic.createBeanByJavaCode.impl.ShanhyB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/SpringBean")
public class TestController {

    @Resource
    private ShanhyA shanhyA;

    @Autowired
    @Qualifier("shanhyB")
    private ShanhyB shanhyB;

    @RequestMapping("/test")
    public String test(){
        shanhyA.display();
        shanhyB.display();
        return "ok";
    }

}
