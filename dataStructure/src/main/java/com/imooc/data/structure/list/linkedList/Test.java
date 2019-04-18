package com.imooc.data.structure.list.linkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        List<String> list = new LinkedList<>();
        MyLinkedList<String> list = new MyLinkedList();
        list.add("1");
        list.add("2");
        list.add("3");

        list.add("4");
        list.add("5");


        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()){
            String str = iterator.next();
            System.out.println(str);
            if ( str.equals("2")) {
                iterator.remove();
            }
            System.out.println(str);
        }


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
