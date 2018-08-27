package com.imooc.firstappdemo.thread;

import java.lang.reflect.Field;
import java.util.concurrent.*;

public class test {
    public static void main(String[] args) {
        Executors.newFixedThreadPool(15);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        //三种阻塞队列
        new ArrayBlockingQueue<Runnable>(5);
        new LinkedBlockingQueue<Runnable>(5);
        new SynchronousQueue<Runnable>();

        executor.prestartCoreThread();

//        for (int i = 0; i < 15; i++) {
//            MyTask myTask = new MyTask(i);
//            executor.execute(myTask);
//            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
//                    executor.getQueue().size() + "，已执行玩别的任务数目：" + executor.getCompletedTaskCount());
//        }


        try {
            Class clazz = Class.forName("java.util.concurrent.ThreadPoolExecutor");

            Field[] fields = clazz.getFields();
            Field field = clazz.getDeclaredField("COUNT_BITS");
            field.setAccessible(true);
            System.out.println(field.get(executor));
            Field field1 = clazz.getDeclaredField("RUNNING");
            field1.setAccessible(true);
            System.out.println(field1.get(executor));
            Field field2 = clazz.getDeclaredField("SHUTDOWN");
            field2.setAccessible(true);
            System.out.println(field2.get(executor));
            Field field3 = clazz.getDeclaredField("STOP");
            field3.setAccessible(true);
            System.out.println(field3.get(executor));
            Field field4 = clazz.getDeclaredField("TIDYING");
            field4.setAccessible(true);
            System.out.println(field4.get(executor));
            Field field5 = clazz.getDeclaredField("TERMINATED");
            field5.setAccessible(true);
            System.out.println(field5.get(executor));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    @Override
    public void run() {
        System.out.println("正在执行task " + taskNum);
        try {
            Thread.currentThread().sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task " + taskNum + "执行完毕");
    }
}