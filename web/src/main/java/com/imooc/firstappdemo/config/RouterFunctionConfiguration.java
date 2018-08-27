package com.imooc.firstappdemo.config;

import com.imooc.firstappdemo.domain.User;
import com.imooc.firstappdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * 路由器函数 配置
 */
@Configuration
public class RouterFunctionConfiguration {

    /**
     * Servlet:
     * 请求接口：ServletRequest或者HttpServletRequest
     * 响应接口：ServlerResponse或者HTTPServletResponse
     * Spring5.0重新定义了服务请求和响应接口
     * 请求接口 ：ServerRequest
     * 响应接口：ServerResponse
     * 既可支持Servlet规范，又可支持自定义，比如Netty （Web Server）
     * 依本例
     * 定义GET请求，并且返回所有的用户对象，URL：/person/find/all
     * Flux是0-N个对象集合
     * Mono是0-1个对象的集合
     * Reactive中的Flux和Mono 它是异步处理（非阻塞）
     * 集合对象基本上是同步处理（阻塞）
     * Flux或者Mono都是publisher
     */

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository){
        //返回所有用户对象
        Collection<User> users = userRepository.findAll();

        return RouterFunctions.route(RequestPredicates.GET("/person/find/all"),
                request -> {
                    Flux<User> userFlux = Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux,User.class);
                });
    }
}
