package com.imooc.firstappdemo.jdk8.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
    }

    public List init() {
        Random random = new Random();
        List<Student> stuList = new ArrayList<Student>() {
            {
                for (int i = 0; i < 100; i++) {
                    add(new Student("student" + i, random.nextInt(50) + 50));
                }
            }
        };
        return stuList;
    }
    public class Student {
        private String name;
        private Integer score;
        //-----getters and setters-----

        public Student(String name, Integer score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }
}
