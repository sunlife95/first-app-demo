package com.imooc.firstappdemo.springBasic.aop.aopClass;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 实现Wen层的日志切面
 */
@Aspect
@Component
@Order(-5)
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 格式：
     * <p>
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
     * 括号中各个pattern分别表示：
     * <p>
     * 修饰符匹配（modifier-pattern?）
     * 返回值匹配（ret-type-pattern）可以为*表示任何返回值,全路径的类名等
     * 类路径匹配（declaring-type-pattern?）
     * 方法名匹配（name-pattern）可以指定方法名 或者 *代表所有, set* 代表以set开头的所有方法
     * 参数匹配（(param-pattern)）可以指定具体的参数类型，多个参数间用“,”隔开，各个参数也可以用“*”来表示匹配任意类型的参数，如(String)表示匹配一个String参数的方法；(*,String) 表示匹配有两个参数的方法，第一个参数可以是任意类型，而第二个参数是String类型；可以用(..)表示零个或多个任意参数
     * 异常类型匹配（throws-pattern?）
     * 其中后面跟着“?”的是可选项
     * 例：
     * 1）execution(* *(..))
     //表示匹配所有方法
     2）execution(public * com. savage.service.UserService.*(..))
     //表示匹配com.savage.server.UserService中所有的公有方法
     3）execution(* com.savage.server..*.*(..))
     //表示匹配com.savage.server包及其子包下的所有方法
     */
    @Pointcut("execution(public * com.imooc..aop.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        //接收到的请求，记录请求内容
        logger.info("WebLogAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //记录下请求内容
        logger.info("URL:"+request.getRequestURL().toString());
        logger.info("HTTP_METHOD:"+request.getMethod());
        logger.info("IP:"+request.getRemoteAddr());
        logger.info("CLASS_METHOD:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        logger.info("ARGS:"+ Arrays.toString(joinPoint.getArgs()));

        //获取所有参数方法一
        Enumeration<String> enu = request.getParameterNames();
        while(enu.hasMoreElements()){
            String paramName = (String)enu.nextElement();
            System.out.println(paramName+":"+request.getParameter(paramName));
        }

    }

    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint){
        //处理完请求，返回内容
        logger.info("WebLogAspect.doAfterReturning()");

    }
}
