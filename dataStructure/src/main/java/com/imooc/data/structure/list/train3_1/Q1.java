package com.imooc.data.structure.list.train3_1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author sunlife
 * @date 2019/4/18 16:11
 */
public class Q1 {
    public static <AnyType> void printLots(List<AnyType> L, List<Integer> P) {
        Iterator<AnyType> iterL = L.iterator();
        Iterator<Integer> iterP = P.iterator();

        AnyType itemL = null;
        Integer itemP = 0;
        int start = 0;

        while (iterL.hasNext() && iterP.hasNext()) {
            itemP = iterP.next();
            System.out.println("looking for position : " + itemP);

            while (start < itemP && iterL.hasNext()) {
                start++;
                itemL = iterL.next();
            }
            System.out.println(itemL);
        }
    }

    public static void main(String[] args) {
        List<String> A = new ArrayList<>();
        A.add("1");
        A.add("2");
        A.add("3");
        A.add("4");
        A.add("5");
        A.add("6");
        A.add("7");
        A.add("8");
        A.add("9");
        A.add("10");
        List<Integer> B = new ArrayList<>();
        B.add(3);
        B.add(5);
        B.add(7);
        printLots(A, B);
    }
}
