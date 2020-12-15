package com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro;/**
 * CREATE BY LiuG ON 2020/11/12.
 * INFO --
 */

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <Description> ShiroRealmsConfiguration<br>
 *
 * @author LiuG<br>
 * @version 1.0<br>
 * @CreateDate 2020/11/12 <br>
 * @see com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro <br>
 * @since V1.0<br>
 */
@Configuration
public class ShiroRealmsConfiguration {

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
        return bean;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(certifyUserRealm());
        return securityManager;
    }
}
