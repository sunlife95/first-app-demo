package com.imooc.algorithm.MaxChildrenArray;

public class MaxSubSum {
    public static int maxSubSum4(int[] a) {
        int maxSum = 0, thisSum = 0;

        for (int i = 0; i < a.length; i++) {
            thisSum += a[i];

            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] a = new int[]{-2, 11, -4, 13, -5, -2};
        System.out.println(maxSubSum4(a));
    }
}
