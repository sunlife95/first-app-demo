package com.imooc.firstappdemo.utils;

import java.util.Arrays;

public class StringUtil {
    public static void main(String[] args) {
        String s1 = new String("hello world!");
        System.out.println(Arrays.toString(s1.split(" ")));
    }
}
