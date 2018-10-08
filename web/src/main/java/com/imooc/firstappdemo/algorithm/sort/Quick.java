package com.imooc.firstappdemo.algorithm.sort;

import com.imooc.firstappdemo.algorithm.Base;

public class Quick {
    public static void main(String[] args) {
        sort();
    }

    public static void sort() {
        Base.soutArr(quickSort(Base.array, 0, Base.array.length - 1));
        System.out.println("\n");
        Base.soutArr(quickSort1(Base.array, 0, Base.array.length - 1));
    }

    public static int[] quickSort(int[] A, int left, int right) {
        int[] arr = A;
        if (left < right) {
            // 一次划分
            int mid = partion(A, left, right);
            quickSort(A, 0, mid - 1);
            quickSort(A, mid + 1, right);
        }
        return arr;
    }

    public static void swap(int[] A, int l, int r) {
        int tmp = A[l];
        A[l] = A[r];
        A[r] = tmp;

    }

    public static int partion(int[] a, int left, int right) {
        // 轴值，默认选取数组的第一个数字
        while (left < right) {
            while (left < right && a[left] <= a[right]) {
                right--;
            }
            if (left < right) {
                swap(a, left, right);
            }
            while (left < right && a[left] <= a[right]) {
                left++;
            }
            if (left < right) {
                swap(a, left, right);
            }
        }
        return left;
    }

    public static void swap2(int[] A, int left, int right) {
        int tmp = A[left];
        A[left] = A[right];
        A[right] = tmp;
    }

    public static int doFast(int[] a, int left, int right) {
        while (left < right) {
            while (left < right && a[left] <= a[right]) {
                right--;
            }
            if (left < right)
                swap2(a, left, right);
            while (left < right && a[left] <= a[right]) {
                left++;
            }
            if (left < right)
                swap2(a, left, right);
        }
        return left;
    }

    public static int[] quickSort1(int[] a,int left,int right) {
        if (left<right){
            int mid = doFast(a,left,right);
            quickSort1(a, left,mid-1);
            quickSort1(a,mid+1,right);
        }
        return a;
    }
}
