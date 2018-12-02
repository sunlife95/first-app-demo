package com.imooc.data.structure.generic;

public class IntToBinary {
    public static void main(String[] args) {
        int data = -7;
        String binaryStr = Integer.toBinaryString(data);
        System.out.println(binaryStr);
        System.out.println(binaryStr.length());
        System.out.println(Integer.toBinaryString(data));
        System.out.println(new IntToBinary().get1Num(data));
    }


    private int get1Num(int data) {
        if (data < 0){
            data = data;
        }
        if (data == 0) {
            return 0;
        }
        if (data % 2 == 1)
            return get1Num(data / 2) + 1;
        else
            return get1Num(data / 2);
    }
}
