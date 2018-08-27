package com.imooc.firstappdemo.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 主要是@Configuration，实现接口：EnvironmentAware就能获取到系统环境信息;
 *
 * @author sunlingfu
 * @version v.0.1
 */
@Configuration
public class MyEnvironmentAware implements EnvironmentAware {

    //注入application.properties的属性到指定变量中
    @Value("${spring.datasource.url}")
    private String myUrl;

    /**
     *注意重写的方法 setEnvironment 是在系统启动的时候被执行。
     */

    @Override
    public void setEnvironment(Environment environment) {
        //打印注入的属性信息
        System.out.println("myUrl:"+myUrl);

        //通过environment获取到系统属性
        System.out.println("JAVA_HOME:"+environment.getProperty("JAVA_HOME"));

        //通过environment同样能获取到application.application配置的属性
        System.out.println("spring.datasource.url:"+environment.getProperty("spring.datasource.url"));

        //获取到前缀是"spring.datasource."的属性列表
        System.out.println("JAVA_HOME:"+System.getenv().get("JAVA_HOME"));
        Map map = System.getenv();
        List<String> list = new ArrayList<String>(map.keySet());
        Collections.sort(list);
        for (String key :list) {
            System.out.println(key+":"+map.get(key));
        }
    }
}
