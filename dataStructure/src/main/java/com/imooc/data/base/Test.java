package com.imooc.data.base;

import java.util.concurrent.TimeUnit;

/**
 * @author sunlife
 * @date 2019/7/15 17:24
 */
public class Test {
    class A {
        public String name;

        @Override
        public String toString() {
            return "A{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    class B extends A {
        public int age;

        @Override
        public String toString() {
            return "B{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    class C {
        public String name;

        @Override
        public String toString() {
            return "A{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    public void test(){
        B b = new B();
        b.name = "slf";
        b.age = 15;
        A a = (A)b ;
        System.out.println(a);
        B c = (B)a;
        System.out.println(c);
        System.out.println(b instanceof A);
        System.out.println(a instanceof A);
        System.out.println(a instanceof B);
    }

    public static void main(String[] args) {
//        new Test().test();
        Integer a = null;
        if (a == null) {
            System.out.println(123);
        }
        String b = "86IeL4Xerjkq8Hha6341ayLab3Vl1Xnt";
        System.out.println(b);
        System.out.println(b.toLowerCase());
    }
}
