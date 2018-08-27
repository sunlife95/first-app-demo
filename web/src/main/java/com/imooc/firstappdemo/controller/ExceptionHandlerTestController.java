package com.imooc.firstappdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionHandlerTestController {
    @RequestMapping("/ExceptionHandlerTestController")
    public void test(){
        int[] ints = new int[3];
        //ArrayIndexOutOfBoundsException
        //System.out.println(ints[3]);
        //ArithmeticException
        System.out.println(100/0);
    }
}
