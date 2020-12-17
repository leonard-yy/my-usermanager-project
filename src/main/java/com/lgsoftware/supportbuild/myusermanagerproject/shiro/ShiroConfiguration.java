package com.lgsoftware.supportbuild.myusermanagerproject.shiro;/**
 * CREATE BY LiuG ON 2020/11/12.
 * INFO --
 */

import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <Description> ShiroRealmsConfiguration<br>
 *
 * @author LiuG<br>
 * @version 1.0<br>
 * @CreateDate 2020/11/12 <br>
 * @see com.lgsoftware.supportbuild.myusermanagerproject <br>
 * @since V1.0<br>
 */
@Configuration
public class ShiroConfiguration {

    private static final String DEFAULT_ROLES = "ROLE_ADMIN";

    private static final String USER_SERVICE_REALM_NAME = CustomUserRealm.REALM_NAME;

    @Bean
    public CustomUserRealm certifyUserRealm() {
        CustomUserRealm realm = new CustomUserRealm();
        realm.setName(USER_SERVICE_REALM_NAME);
        // 密码校验
        CustomCredentialsMatch customMatch = new CustomCredentialsMatch();
        customMatch.setHashAlgorithmName("md5");
        realm.setCredentialsMatcher(customMatch);
        // 添加默认的角色
        realm.setDefaultRoles(DEFAULT_ROLES);
        // 设置可处理的Token
        realm.setAuthenticationTokenClass(CustomToken.class);

        return realm;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        // 认证定义
        Map<String, Filter> filters = bean.getFilters();
        filters.put("authc", myAuthc());

        // 过滤器
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("/dologin**", "anon");
        map.put("/index.html", "authc");
        map.put("/**/**", "authc");
        bean.setFilterChainDefinitionMap(map);

        bean.setLoginUrl("/dologin.html");
        return bean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(certifyUserRealm());
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     *
     */
    private CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMe = new CookieRememberMeManager();
        byte[] decode = Base64.decode("6AvVhmFLUs0KTA3Kprsdag==");
        rememberMe.setCipherKey(decode);
        return rememberMe;
    }

    /**
     * 自定义过滤器
     *
     * @return
     */
    private MyShiroAjaxFormAuthenticationFilter myAuthc() {
        MyShiroAjaxFormAuthenticationFilter filter = new MyShiroAjaxFormAuthenticationFilter();
        return filter;
    }

}
