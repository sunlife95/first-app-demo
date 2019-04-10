package com.imooc.algorithm.gcd;

/**
 * @author sunlife
 * @date 2019/4/10 16:24
 */
public class GCD {
    public static long gcd (long m , long n ){
        if (m < n){
            long tmp = m ;
            m = n ;
            n = tmp;
        }
        while (n != 0){
            long rem = m % n ;
            m = n;
            n = rem;
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println(gcd(15,50));
        System.out.println(gcd(1989,1590));
    }
}
