package com.imooc.firstappdemo.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;

/**
 * 监听Session的创建与销毁
 *
 */
@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    private Date begin;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session 被创建");
    }
    /**
     * session被销毁的时候调用此方法
     * session销毁的方法有两种：
     * ①controller中调用session.invalidate()方法销毁session
     * ②当访问网站的浏览器关闭的时候
     * ②application.yml中设置session过期时间（如何设置?）
     * @param se
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("sessionDestroyed-MyHttpSessionListener");
    }
}
