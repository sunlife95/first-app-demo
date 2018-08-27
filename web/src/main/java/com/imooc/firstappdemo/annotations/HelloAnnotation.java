package com.imooc.firstappdemo.annotations;

public class HelloAnnotation {
    public HelloAnnotation() {
    }

    @MyAnnotation(author = "Flm", priority = MyAnnotation.Priority.MEDIUM, status = MyAnnotation.Status.STARTED)
    public void test1() {
        System.out.println("testAnnotation");
    }
}
