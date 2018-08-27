package com.imooc.firstappdemo;

		import com.alibaba.fastjson.serializer.SerializerFeature;
		import com.alibaba.fastjson.support.config.FastJsonConfig;
		import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
		import com.imooc.firstappdemo.propertiesBean.MySettings;
		import org.springframework.boot.SpringApplication;
		import org.springframework.boot.autoconfigure.SpringBootApplication;
		import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
		import org.springframework.boot.context.properties.EnableConfigurationProperties;
		import org.springframework.boot.web.servlet.ServletComponentScan;
		import org.springframework.cache.annotation.EnableCaching;
		import org.springframework.context.annotation.Bean;
		import org.springframework.context.annotation.Import;
		import org.springframework.http.converter.HttpMessageConverter;
		import org.springframework.scheduling.annotation.EnableAsync;
		import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
		import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties(MySettings.class)
@EnableCaching //启动缓存
@EnableAsync //启用异步调用
public class FirstAppDemoApplication extends WebMvcConfigurerAdapter{

	public static void main(String[] args) {
		SpringApplication.run(FirstAppDemoApplication.class, args);
	}

	//使用fastjson
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters(){
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}
	//增加/myres/**映射到classpath:/myres/*

	/**
	 * 这样使用代码的方式自定义目录映射，并不影响Spring Boot的默认映射，可以同时使用。
	 *如果我们将/myres/* 修改为 /* 与默认的相同时，则会覆盖系统的配置，可以多次使用 addResourceLocations 添加目录，优先级先添加的高于后添加的。
	 * 使用外部目录
	 *如果我们要指定一个绝对路径的文件夹（如 D:/data/api_files ），则只需要使用 addResourceLocations 指定即可。
	 * 可以直接使用addResourceLocations 指定磁盘绝对路径，同样可以配置多个位置，注意路径写法需要加上file:
	 *registry.addResourceHandler("/api_files/**").addResourceLocations("file:D:/data/api_files");
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/myres/**").addResourceLocations("classpath:/myres/");

		super.addResourceHandlers(registry);
	}
}
