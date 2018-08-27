package com.imooc.firstappdemo.annotations;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
@EnableCaching
public class AnnotationTest {

    public static void main(String[] args) {
        Class clazz = HelloAnnotation.class;//得到字节码文件对象

        //得到该类及父类中的所有方法
        Method[] methods = clazz.getMethods();

        for (Method m : methods) {
            //判断方法是否使用了@MyAnnotation注解
            boolean boo = m.isAnnotationPresent(MyAnnotation.class);
            System.out.println(m.getName() + "=============" + boo);//都是false默认注解存活到CLASS，改变存活到RUNTIME
            if (boo) {
                try {
                    m.invoke(clazz.newInstance(), null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
