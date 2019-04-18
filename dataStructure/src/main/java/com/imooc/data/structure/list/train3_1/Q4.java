package com.imooc.data.structure.list.train3_1;

import com.imooc.data.structure.base.CollectionsAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author sunlife
 * @date 2019/4/18 17:09
 */
public class Q4 {
    public static <AnyType extends Comparable<? super AnyType>> void  intersection(List<AnyType> L1, List<AnyType> L2, List<AnyType> L3) {
        Iterator<AnyType> I1 = L1.iterator();
        Iterator<AnyType> I2 = L2.iterator();
        AnyType item1 = null,item2 = null ;

        if (I1.hasNext() && I2.hasNext()) {
            item1 = I1.next();
            item2 = I2.next();
        }

        while (item1 != null && item2 != null) {
            int compareResult = item1.compareTo(item2);

            if (compareResult == 0) {
                L3.add(item1);
                item1 = I1.next();
                item2 = I2.next();
            } else if (compareResult < 0){
                item1 = I1.hasNext() ? I1.next() : null;
            }else if (compareResult > 0){
                item2 = I2.hasNext() ? I2.next() : null;
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
        intersection(l1, l2, l3);
        for (Integer i : l3) {
            System.out.println(i);
        }
    }
}
