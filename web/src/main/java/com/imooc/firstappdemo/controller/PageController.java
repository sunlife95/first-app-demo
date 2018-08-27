package com.imooc.firstappdemo.controller;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

@Controller
public class PageController implements EnvironmentAware{
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("PageController"+environment.getProperty("JAVA_HOME"));
    }
}
