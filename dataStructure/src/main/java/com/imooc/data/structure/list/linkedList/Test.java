package com.imooc.data.structure.list.linkedList;

import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("123123");
        list.add("123123");
        list.add("123123");
        list.add("123123");
        list.add("123123");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.get(4));


        com.imooc.data.structure.list.linkedList.LinkedList<String > testList = new com.imooc.data.structure.list.linkedList.LinkedList<String>();

        testList.linkLast("nihao ");
        testList.linkLast("world ");
        testList.linkLast("zeze ");

    }
}
