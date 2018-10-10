package com.imooc.firstappdemo.base.proxy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PeopleHandler implements InvocationHandler {
    private People people;

    public PeopleHandler(People people) {
        this.people = people;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke ");
        System.out.println("method:"+method.getName());

        method.invoke(people,args);

        System.out.println("after invoke ");


        return null;
    }
}
