package cn.realphago.springbootshiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.realphago.springbootshiro.shiro.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 9:29
 * @describe 用来配置shiro环境
 */
@Configuration
public class ShiroConfig {

    //负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> resourceMap = new HashMap<String, String>();
        resourceMap.put("/**", "authc");
        resourceMap.put("/css/**", "anon");
        resourceMap.put("/fonts/**", "anon");
        resourceMap.put("/js/**", "anon");
        resourceMap.put("/lib/layui/**", "anon");
        resourceMap.put("/images/**", "anon");
        resourceMap.put("/user/login", "anon");
        resourceMap.put("/product/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(resourceMap);
        shiroFilterFactoryBean.setLoginUrl("/");
        return shiroFilterFactoryBean;
    }

    @DependsOn("lifecycleBeanPostProcessor")
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setUsePrefix(true);
        return proxyCreator;
    }

    //创建安全管理器
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher("md5");
        credentialsMatcher.setHashIterations(1024);
        return credentialsMatcher;
    }

    @Bean()
    public Realm realm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        CustomerRealm customerRealm = new CustomerRealm();
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return customerRealm;
    }

    @Bean()
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


}
