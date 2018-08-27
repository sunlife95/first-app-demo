package com.imooc.firstappdemo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(value =ArithmeticException.class)
    @ResponseBody
    public String defaultErrorHandler(HttpServletRequest req,Exception e){
        e.printStackTrace();
        System.out.println("自定义异常处理器");
        return "error";
    }
}
