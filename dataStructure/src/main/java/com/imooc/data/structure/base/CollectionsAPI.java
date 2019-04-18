package com.imooc.data.structure.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author sunlife
 * @date 2019/4/11 17:14
 */
public class CollectionsAPI {
    static int A = 0;
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(0,3);
        for (Integer i : list) {
            System.out.println(i);
        }
        Iterator iterator = list.iterator();

        list.remove(0);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}
