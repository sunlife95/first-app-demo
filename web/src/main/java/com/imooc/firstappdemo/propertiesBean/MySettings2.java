package com.imooc.firstappdemo.propertiesBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@PropertySource(value="classpath:config/myProperties.properties")
public class MySettings2 {
    @Value("${lf.name}")
    private String name;
    @Value("${lf.age}")
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MySettings2{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
