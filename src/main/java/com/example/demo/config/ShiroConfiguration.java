package com.example.demo.config;

import com.example.demo.realm.JWTFilter;
import com.example.demo.realm.MyShiroRealm;
import com.example.demo.utils.MySessionManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Oliver Seth on 2020/2/3 19:12
 */
@Configuration
public class ShiroConfiguration {
    @Bean
    public MyShiroRealm getRealm(){
        return new MyShiroRealm();
    }

    @Bean
    public DefaultWebSecurityManager getSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(getRealm());
        defaultWebSecurityManager.setSessionManager(sessionManager());
        defaultWebSecurityManager.setCacheManager(cacheManager());
        return defaultWebSecurityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilter=new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("JWTFilter", new JWTFilter());
        shiroFilter.setFilters(filterMap);
        Map<String, String> filterChainDefinitionMap = shiroFilter.getFilterChainDefinitionMap();
        filterChainDefinitionMap.put("/user/login","anon");
        filterChainDefinitionMap.put("/user/register","anon");
        filterChainDefinitionMap.put("/**", "JWTFilter");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        shiroFilter.setLoginUrl("/user/auth?code=1");
//        shiroFilter.setUnauthorizedUrl("/user/auth?code=2");
        return shiroFilter;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);

        return defaultAdvisorAutoProxyCreator;
    }

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private Integer timeout;

    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        redisManager.setPassword(password);
        redisManager.setTimeout(timeout);
        return redisManager;
    }

    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    public DefaultWebSessionManager sessionManager(){
        MySessionManager sessionManager=new MySessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager= new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }
}
