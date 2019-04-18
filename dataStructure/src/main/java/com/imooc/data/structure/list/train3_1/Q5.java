package com.imooc.data.structure.list.train3_1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author sunlife
 * @date 2019/4/18 17:48
 */
public class Q5 {
    public static <AnyType extends Comparable<? super AnyType>> void union(List<AnyType> L1, List<AnyType> L2, List<AnyType> L3) {
        Iterator<AnyType> i1 = L1.iterator();
        Iterator<AnyType> i2 = L2.iterator();

        AnyType item1 = null, item2 = null;

        if (i1.hasNext()) {
            item1 = i1.next();
        }
        if (i2.hasNext()) {
            item2 = i2.next();
        }

        while (item1 != null || item2 != null) {
            int compareResult = 0;
            if (item1 != null && item2 != null) {
                compareResult = item1.compareTo(item2);
            } else if (item1 == null) {
                compareResult = 1;
            } else if (item2 == null) {
                compareResult = -1;
            }
            if (compareResult == 0) {
                L3.add(item1);
                item1 = i1.hasNext() ? i1.next() : null;
                item2 = i2.hasNext() ? i2.next() : null;
            } else if (compareResult < 0) {
                L3.add(item1);
                item1 = i1.hasNext() ? i1.next() : null;
            } else if (compareResult > 0) {
                L3.add(item2);
                item2 = i2.hasNext() ? i2.next() : null;
            }
        }
    }


    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>();
        l1.add(1);
        l1.add(4);
        l1.add(5);
        l1.add(8);
        l1.add(10);
        l1.add(11);
        List<Integer> l2 = new ArrayList<>();
        l2.add(2);
        l2.add(3);
        l2.add(5);
        l2.add(6);
        l2.add(9);
        l2.add(13);
        List<Integer> l3 = new ArrayList<>();
        union(l1, l2, l3);
        for (Integer i : l3) {
            System.out.println(i);
        }
    }
}
