package com.imooc.firstappdemo.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put(null,"hello world");

        Map<String,String> m = Collections.synchronizedMap(map);
        map.get(null);
    }
}
