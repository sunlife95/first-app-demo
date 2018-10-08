package com.imooc.firstappdemo.algorithm.sort;

import com.imooc.firstappdemo.algorithm.Base;

public class bubble {
    public static int[] sort(){
        int[] arr = Base.array;
        for (int i = 0 ; i<arr.length;i++){
            for (int j = i + 1 ;j<arr.length;j++){
                if (arr[i]>arr[j]){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Base.soutArr(sort());
    }
}
