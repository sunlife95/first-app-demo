package com.imooc.data.structure.generic;

public class OutPutDouble {
    public static void output(double dou){
        String mathDou = Math.abs(dou)+"";
        System.out.println(mathDou);
        System.out.println(mathDou.indexOf("."));
        String[] strArr = mathDou.split("\\.");
        if (dou < 0){
            System.out.print("-");
        }
        printInt(Integer.parseInt(strArr[0]));
        System.out.print(".");
        printInt(Integer.parseInt(strArr[1]));

    }

    private  static void printInt(int printNum){
        if (printNum >= 10 )
            printInt(printNum / 10);
        System.out.print(printNum % 10);
    }

    public static void main(String[] args) {
        output(-12.23);
    }
}
