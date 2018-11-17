package com.imooc.firstappdemo.base.proxy.proxy;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        People men = new Men();

        PeopleHandler handler = new PeopleHandler(men);

        People people = (People)Proxy.newProxyInstance(handler.getClass().getClassLoader(),new Class[]{People.class},handler);

        System.out.println(people.getClass().getName());

        people.eat("米饭");

        people.talk("sunlife");
        System.out.println("----------proxy 参数作用：. 可以使用反射获取代理对象的信息（也就是proxy.getClass().getName()）。\n" +
                "\n" +
                "2. 可以将代理对象返回以进行连续调用，这就是proxy存在的目的，因为this并不是代理对象。---------------");

        PeopleInterfaceHandler pHandler = new PeopleInterfaceHandler();

        People people1 = (People)Proxy.newProxyInstance(pHandler.getClass().getClassLoader(),new Class[]{People.class},pHandler);

        people1.talk("sunlife").talk("sunyaqin").talk("you");

        people1.eat("photo");
    }
}
