package com.imooc.data.structure.list.train3_1;

import java.util.*;

/**
 * @author sunlife
 * @date 2019/4/19 11:29
 */
public class Q6 {
    public static void main(String[] args) {
        System.out.println(5%1);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        ((ArrayList<String>) list).trimToSize();
        long beginTime = new Date().getTime();
        josephus(list, 10);
        long endTime = new Date().getTime();
        System.out.println(endTime - beginTime);
        System.out.println(beginTime);
        System.out.println(endTime);
        long beginTime2 = new Date().getTime();
        pass(10,6);
        long endTime2 = new Date().getTime();
        System.out.println(endTime2 - beginTime2);
        System.out.println(beginTime2);
        System.out.println(endTime2);
    }

    public static <AnyType> void josephus(List<AnyType> N, int M) {
        Iterator<AnyType> NT = N.iterator();

        AnyType val = null;
        while (N.size() > 0) {
            for (int i = 0; i < M % N.size() + 1; i++) {
                if (!NT.hasNext())
                    NT = N.iterator();
                val = NT.next();
            }
            System.out.println(val);
            NT.remove();
        }
    }
    public static void pass(int m, int n)
    {
        int i, j, mPrime, numLeft;
        ArrayList<Integer> L = new ArrayList<Integer>();
        for (i=1; i<=n; i++)
            L.add(i);
        ListIterator<Integer> iter = L.listIterator();
        Integer item=0;
        numLeft = n;
        mPrime = m % n;
        for (i=0; i<n; i++)
        {
            mPrime=m% numLeft;
            if (mPrime <= numLeft/2)
            {
                if (iter.hasNext())
                    item = iter.next();
                for (j=0; j<mPrime; j++)
                {
                    if (!iter.hasNext())
                        iter = L.listIterator();
                    item = iter.next();
                }
            }
            else
            {
                for (j=0; j<numLeft-mPrime; j++)
                {
                    if (!iter.hasPrevious())
                        iter = L.listIterator(L.size());
                    item = iter.previous();
                }
            }
            System.out.print("Removed " + item + " ");
            iter.remove();
            if (!iter.hasNext())
                iter = L.listIterator();
            System.out.println();
            for (Integer x:L)
                System.out.print(x + " ");
            System.out.println();
            numLeft--;
        }
        System.out.println();
    }
}
