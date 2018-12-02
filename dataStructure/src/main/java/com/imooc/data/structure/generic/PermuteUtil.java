package com.imooc.data.structure.generic;

public class PermuteUtil {

    public static void main(String[] args) {
        permute("abc");
    }
    public static void permute(String str) {
        permute(str.toCharArray(),0,str.length());
    }

    private static void permute(char[] str, int low, int high) {
        if (low == high) {
            for (int i = 0; i < str.length; i++) {
                System.out.print(str[i]);
            }
            System.out.println();
        } else {
            for (int i = low ; i < str.length ; i++){
                exchange(str,i,low);
                permute(str,low+1 , high);
                exchange(str,i,low);
            }
        }
    }
    private static void exchange(char[] str, int a, int b){
        char tmp = str[a];
        str[a] = str[b];
        str[b] = tmp;
    }
}
