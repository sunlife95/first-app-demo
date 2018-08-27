package com.imooc.firstappdemo.framework.shiro.config;

import com.imooc.firstappdemo.framework.shiro.realm.MyShiroRealm;
import com.imooc.firstappdemo.framework.shiro.session.MyRedisSessionDao;
import org.apache.shiro.authc.RememberMeAuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Angel(QQ:412887952)
 * @version v.0.1
 * @Author:Flm
 * @Description:shiro config
 * @Date:16:15 2018/3/13
 * Shiro 配置
 *
 * Apache Shiro 核心通过 Filter 来实现，就好像SpringMvc 通过DispachServlet 来主控制一样。
 * 既然是使用 Filter 一般也就能猜到，是通过URL规则来进行过滤和权限校验，所以我们需要定义一系列关于URL的规则和访问权限。
 */
@Configuration
public class ShiroConfig {
    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();

        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));

        return hashedCredentialsMatcher;
    }

    /**
     * 身份认证realm;
     * (这个需要自己写，账号密码校验；权限等)
     *
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm
        securityManager.setRealm(myShiroRealm());
        //注入缓存管理器
        securityManager.setCacheManager(ehCacheManager());
//        //注入sessionDao
//        securityManager.setSessionManager(SessionManager());

        //注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());

        SimpleCookie s = (SimpleCookie)(((CookieRememberMeManager)securityManager.getRememberMeManager()).getCookie());
        System.out.println(s);
        return securityManager;
    }

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shiroFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //拦截器
        Map<String, String> filterChainDefinitioinMap = new LinkedHashMap<String, String>();

        //配置退出过滤器，其中的具体的退出代码shiro已经实现
        filterChainDefinitioinMap.put("/shiro/logout", "logout");

        //配置记住我或认证通过可以访问的地址
        filterChainDefinitioinMap.put("/index","user");
        filterChainDefinitioinMap.put("/","user");

        //过滤连定义，从上向下顺序执行，一般将/**放在最为下边 ！！！！！
        //authc：所有的url都必须认证通过才可以访问；anon:所有的url都可以匿名访问
        filterChainDefinitioinMap.put("/shiro/**", "authc");

        //如果不设置默认会自动寻找web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/shiro/login");//此处应是url并非静态资源位置

        //登陆成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/shiro/index");

        //未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitioinMap);

        return shiroFilterFactoryBean;

    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
//    @Bean
//    public EhCacheManager ehCacheManager(){
//        System.out.println("ShiroConfiguration.ehCacheManager()");
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
//        return ehCacheManager;
//    }

    /**
     * 使用自定义的myRedisSessionDao生成的DefaultWebSessionManager
     * @return
     */
//    @Bean
//    @Autowired
//    public DefaultWebSessionManager defaultWebSessionManager(MyRedisSessionDao myRedisSessionDao){
//        DefaultWebSessionManager manager = new DefaultWebSessionManager();
//        manager.setSessionDAO(myRedisSessionDao);
//        manager.setDeleteInvalidSessions(true);// 删除过期的session
//        manager.setGlobalSessionTimeout(myRedisSessionDao.getExpireTime());// 设置全局session超时时间
//        manager.setSessionValidationSchedulerEnabled(true);// 是否定时检查session
//        return manager;
//    }

    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1");
        redisManager.setPort(6379);
        redisManager.setExpire(1800);// 配置过期时间
        // redisManager.setTimeout(timeout);
        // redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     *
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }
    /**
     * shiro session的管理
     */
    public DefaultWebSessionManager SessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
    /*
    EhcacheManager
     */
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }

    /**
     * cookie对象 实现“记住我”功能
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的 name = remeberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //如果httpOnly设置为true，则客户端不会暴露给客户端脚本代码，使用HttpOnly cookie有助于减少某些类型的跨站点脚本攻击
//        simpleCookie.setHttpOnly(true);
        //记住我cookie生效时间30天，单位秒
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }
    /**
     * cookie管理对象
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }
}
