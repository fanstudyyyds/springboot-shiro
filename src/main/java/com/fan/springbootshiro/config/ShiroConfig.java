package com.fan.springbootshiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/*
 * @author  fan
 * @date  2021/9/11 14:29
 * @aa  玉树临风美少年,揽镜自顾夜不眠
 */
@Configuration
public class ShiroConfig {
    @Bean
    //ShiroFilterFactoryBean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro的内置过滤器
        /**
         * anon 无需认证就可访问
         * authc 必须认证
         * user 必须要记住我功能才可以用
         * perms 拥有某个资源权限才能访问
         * role 拥有某个角色才能访问
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();

        //        filterMap.put("/user/add","authc");
        //        filterMap.put("/user/update","authc");

        //授权 未授权跳转的未授权页面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");


        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);

        //设置登入请求
        bean.setLoginUrl("/tologin");
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    //DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象 需要自定义类
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }


    //整合shiroDialect 用来整合 thymeleaf shiro
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
