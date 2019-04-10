package com.imooc.firstappdemo.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static void main(String[] args) throws ParseException {
        Date d1 = new Date(2018,9,3);
        Date d2 = new Date(2017,8,14);
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d1.getTime());
        System.out.println(d2.getTime());
        System.out.println(d1.compareTo(d2));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dateFormat.format(dateFormat.parse(dateFormat.format(new Date()))));

        System.out.println(dateFormat.format(dateFormat.parse("2019-9-12")));

    }
}
