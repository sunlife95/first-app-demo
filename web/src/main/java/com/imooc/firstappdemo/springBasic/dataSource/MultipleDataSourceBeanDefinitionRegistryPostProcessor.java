package com.imooc.firstappdemo.springBasic.dataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationScopeMetadataResolver;
import org.springframework.context.annotation.ScopeMetadataResolver;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态创建多数据源注册到Spring中
 *
 * 接口BeanDefinitionRegistryPostProcessor只要是注入bean
 *
 * 接口EnvironmentAware 重写方法setEnvironment
 * 可以再工程启动时，获取到环境变量和application配置文件中的变量
 *
 * 方法的执行顺序：
 *
 * setEnvironment（）-》postProcessBeanDefinitionRegistry（）-》postProcessBeanFactory()
 * @author Flm
 *
 */
public class MultipleDataSourceBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor,EnvironmentAware{

    Logger logger = LoggerFactory.getLogger(MultipleDataSourceBeanDefinitionRegistryPostProcessor.class);
    //作用域对象
    private ScopeMetadataResolver scopeMetadataResolver = new AnnotationScopeMetadataResolver();

    //bean名称生成器
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    //如配置文件中未指定数据源类型，使用该默认值
    private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";

    //存放DataSource配置的集合
    private Map<String,Map<String,Object>> dataSourceMap = new HashMap<String,Map<String,Object>>();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    }

    /**
     * 注意重写的方法，setEnvironment是在系统启动的时候执行的。
     * 这个方法主要是：加载多数据源配置
     * 从applicatio.properties文件中加载
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("MultipleDataSourceBeanDefinitionRegistryPostProcessor.setEnvironment");

        //获取application.properties配置的多数据源，添加到map中，之后再postProcessBeanDefinitonRegistry进行注册。
        //获取到前缀是cuntom.datasource.的属性列表值


    }
}
