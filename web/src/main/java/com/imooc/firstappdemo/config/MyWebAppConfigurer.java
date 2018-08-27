package com.imooc.firstappdemo.config;

import com.imooc.firstappdemo.HandlerInterceptor.MyInterceptor;
import com.imooc.firstappdemo.HandlerInterceptor.MyInterceptor1;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //多个拦截器组成一个拦截器链
        //addPathPatterns用于添加拦截规则
        //excludePathPatterns用户排除拦截
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
        registry.addInterceptor(new LocaleChangeInterceptor());
        super.addInterceptors(registry);
    }
}
