package com.justgo.admin.shrio;

import com.justgo.admin.entity.SysResource;
import com.justgo.admin.service.SysResourceService;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * author : WUJINGZHI
 * date: 2017/8/30 14:42
 */
@Configuration
public class ShiroConfig {
    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(MyRealm adminRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(adminRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //登录页面
        shiroFilter.setLoginUrl("/user/loginPage");
        // 登录成功后要跳转的连接
        //shiroFilter.setSuccessUrl("/user");
        //拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user/loginPage", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/security/unauthorized", "anon");
        filterChainDefinitionMap.put("/mainIndex", "authc");
        filterChainDefinitionMap.put("/user/getMenu*", "authc");
        filterChainDefinitionMap.put("/security/userInfo", "authc");
        filterChainDefinitionMap.put("/security/modifyPassword", "authc");
        filterChainDefinitionMap.put("/", "authc");
        //静态资源
        filterChainDefinitionMap.put("/static/**", "anon");
        //报表demo不拦截
        filterChainDefinitionMap.put("/rd/**", "anon");

        filterChainDefinitionMap.put("/knowledgePoint/downloadFile/*", "anon");
        filterChainDefinitionMap.put("/keyPoint/downloadFile/*", "anon");
        filterChainDefinitionMap.put("/questionAnswer/downloadFile/*", "anon");
        List<SysResource> sysResources = sysResourceService.selectAllPermissions();
        sysResources.forEach(sysResource ->
                filterChainDefinitionMap
                        .put(sysResource.getResourceUrl(), "perms[" + sysResource.getPermission() + "]"));
        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/user/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "roles[admin]");
        // filterChainDefinitionMap.put("/**", "anon");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilter.setUnauthorizedUrl("/security/unauthorized");

        return shiroFilter;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager 安全管理器
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /*@Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }*/
}
