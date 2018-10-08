package com.imooc.firstappdemo.utils;

import java.util.Date;

public class DateUtil {
    public static void main(String[] args) {
        Date d1 = new Date(2018,9,3);
        Date d2 = new Date(2017,8,14);
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d1.getTime());
        System.out.println(d2.getTime());
        System.out.println(d1.compareTo(d2));
    }
}
