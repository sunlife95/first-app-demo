package com.imooc.firstappdemo.JDK8.stream;

import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    //创建stream
    @org.junit.Test
    public void testArrayStream() {
        //1通过Arrays.stream
        //1.1 基本类型
        int[] arr = new int[]{1, 2, 4, 8, 6, 98, 74, 56, 76};
        IntStream intStream = Arrays.stream(arr);
        //1.2引用类型
        Student[] students = new Student[]{new Student(1, "aaa"), new Student(2, "ccc")};
        Stream<Student> strudentStream = Arrays.stream(students);

        strudentStream.map(Student::getName).collect(Collectors.toList()).forEach(System.out::println);
        //2通过stream.of
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5, 6);
        //注意生成的是int[]的流
        Stream<int[]> stream = Stream.of(arr, arr);

        intStream.forEach(System.out::println);
//        strudentStream.forEach(System.out::println);
        stream1.forEach(System.out::println);
        stream.forEach(System.out::println);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("nihao !");
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            System.out.println("hello");
        });
        t2.start();
    }

    class Student {
        private int age;
        private String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
