package com.imooc.firstappdemo.springBasic.createBeanByJavaCode.config;

import com.imooc.firstappdemo.springBasic.createBeanByJavaCode.impl.ShanhyA;
import com.imooc.firstappdemo.springBasic.createBeanByJavaCode.impl.ShanhyB;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor{
    //Bean的名称生成器
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor.postProcessBeanFactory()");
        //设置属性,例如
//        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("dataSourceA");
//        MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();
//        //加入属性
//        mutablePropertyValues.addPropertyValue("driverClassName", "com.mysql.jdbc.Driver");
//        mutablePropertyValues.addPropertyValue("url", "jdbc:mysql://localhost:3306/test");
//        mutablePropertyValues.addPropertyValue("username", "root");
//        mutablePropertyValues.addPropertyValue("password", "123456");
    }

    /**
     * 先执行postProcessBeanDefinitionRegistry()方法
     * 再执行postProcessBeanFactory()方法
     * @param beanDefinitionRegistry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("MyBeanDefinitionRegistryPostProcessor.postProcessBeanDefinitionRegistry()");

        /**
         * 注册Bean
         */
        registerBean(beanDefinitionRegistry,"shanhyA",ShanhyA.class);
        registerBean(beanDefinitionRegistry,"shanhyB",ShanhyB.class);
    }

    /**
     *
     * @param registry
     * @param name
     * @param beanClass
     */
    private void registerBean(BeanDefinitionRegistry registry,String name,Class<?> beanClass){
        AnnotatedBeanDefinition annotatedBeanDefinition = new AnnotatedGenericBeanDefinition(beanClass);
        //可以自动生成name
        String beanName = (name != null)?name:this.beanNameGenerator.generateBeanName(annotatedBeanDefinition,registry);
        //bean注册的holer类
        BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(annotatedBeanDefinition,beanName);
        //使用bean注册工具类进行注册
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder,registry);
    }
}
