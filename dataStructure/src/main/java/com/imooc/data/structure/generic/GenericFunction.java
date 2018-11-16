package com.imooc.data.structure.generic;

import java.util.Arrays;
import java.util.Comparator;

public class GenericFunction {
    public static <AnyType extends Comparable<? super AnyType>> AnyType findMax(AnyType[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    public static void main(String[] args) {
        int[] arr = {21, 12, 43, 34, 15, 7, 89, 97, 54,23};
        System.out.println(choose(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int choose(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int k = arr.length / 2;
        int[] tmpArr = Arrays.copyOf(arr, k);
        Arrays.sort(tmpArr);
        reverse(tmpArr);
        for (int i = k; i < arr.length; i++) {
            if (tmpArr[k - 1] < arr[i]) {
                for (int j = 0; j < tmpArr.length; j++) {
                    if (arr[i] > tmpArr[j] ){
                        sortInsert(tmpArr,j,arr[i]);
                        break;
                    }
                }
            }
        }
        return tmpArr[k-1];
    }
    public static void reverse(int[] arr) {
        int middle = arr.length / 2;

        for (int i = 0; i < middle; i++) {
            int temp = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = arr[i];
            arr[i] = temp;
        }
    }
    public static void sortInsert(int[] arr ,int pos, int value){
        for (int i =arr.length-1 ; i >pos ; i--) {
            arr[i] = arr[i - 1];
        }
        arr[pos]=value;
    }
}

