package com.imooc.firstappdemo.base.proxy.proxy;

public class Men implements People {
    @Override
    public String eat(String food) {
        System.out.println("eat "+food);
        return "eat "+food;
    }

    @Override
    public People talk(String herName) {
        System.out.println("talk with "+herName);
        return this;
    }
}
