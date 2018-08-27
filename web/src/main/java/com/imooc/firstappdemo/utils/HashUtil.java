package com.imooc.firstappdemo.utils;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashUtil {
    public static void main(String[] args) {
        String s = new String();
        System.out.println(s.hashCode());
        s = "hello world";
        System.out.println(s.hashCode());
        s = "hello worls";
        System.out.println(s.hashCode());
        Map<String,String> map = new HashMap<String,String>();
        map.hashCode();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        int i = 1 << 30;
        System.out.println("i:"+i);
        System.out.println("int:"+new Integer("12").hashCode());
        int a = 16>>1;
        System.out.println(a);
    }

}
