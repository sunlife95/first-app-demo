package com.imooc.firstappdemo.base.proxy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class PeopleInterfaceHandler  implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("eat")){
            return "eat "+ Arrays.toString(args);
        }
        if (method.getName().equals("talk")){
            System.out.println("talk with "+Arrays.toString(args));
            return proxy;
        }

        return null;
    }
}
