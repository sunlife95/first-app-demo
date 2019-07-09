package com.imooc.data.structure.list.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author sunlife
 * @date 2019/6/24 16:38
 */
public class StreamList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.removeIf(integer ->
            integer > 2
        );
        System.out.println(list.stream().filter(integer -> integer > 2).collect(Collectors.toList()));
        System.out.println(list);
    }
}
