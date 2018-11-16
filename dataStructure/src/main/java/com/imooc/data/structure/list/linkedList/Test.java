package com.imooc.data.structure.list.linkedList;

import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        list.add("4");
        list.add("5");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));
        System.out.println("---------------------");
        System.out.println(list.size());
        System.out.println("---------------------");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));


        com.imooc.data.structure.list.linkedList.LinkedList<String > testList = new com.imooc.data.structure.list.linkedList.LinkedList<>();

        testList.linkLast("nihao ");
        testList.linkLast("world ");
        testList.linkLast("zeze ");

        printOut(76234);

        Integer integer = new Integer(15);
        integer++;
        System.out.println(integer);

        int i = 15;
        i++;
        System.out.println(i);
    }

    public static void printOut(int n){
        if (n>=10)
            printOut(n / 10);
        System.out.println(n % 10);
    }
}
