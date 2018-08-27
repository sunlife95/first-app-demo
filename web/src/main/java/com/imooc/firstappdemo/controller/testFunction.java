package com.imooc.firstappdemo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class testFunction {
    public static void main(String[] args) {
        Runnable aaa = () -> System.out.println("hello world");
        aaa.run();
        BinaryOperator<Long> add = (x, y) -> x + y;
        System.out.println(add.apply(new Long(3),new Long(3)));
        List<Integer> list = new ArrayList<Integer>(5);
        list.add(1);
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(5);
        long count = list.stream().filter(i->i>2).count();
        System.out.println(count);
        List<String> collected = Stream.of("a", "b", "c")
                .collect(toList());
        System.out.println(collected);
        List<String> collected1 = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(toList());
        System.out.println(collected1);
        new testFunction().test();
    }
    public void test(){
        List<String> t = Stream.of("a","b","c","d").collect(toList());
        t.forEach(this::prints);
    }
    public void  prints(String s){
        System.out.println(s);
    }
}
