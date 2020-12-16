package com.lgsoftware.supportbuild.myusermanagerproject.shiro;

import com.lgsoftware.supportbuild.myusermanagerproject.shiro.util.RealmUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class CustomUserRealm extends AuthorizingRealm {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String REALM_NAME = "CustomUserRealm";

    private String defaultRoles;

    private String defaultPermissions;

    @Autowired(required = false)

    protected UserService userService;

    public CustomUserRealm() {
    }

    @PostConstruct
    public void checkHasCacheManager() {
        if (this.userService == null) {
            this.logger.error("没有找到【UserService】的实现类，请添加相应实现");
        }
        else {
            this.logger.info("使用【{}】完成认证", this.userService.getClass().getName());
        }
    }

    @Override
    public String getName() {
        return "CustomUserRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        this.logger.debug("获得权限信息");
        if (principals == null) {
            throw new AuthorizationException("Principal对象不能为空");
        }
        else {
            String username = (String) principals.getPrimaryPrincipal();

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            if (this.userService != null) {
                info.setRoles(this.userService.findRoles(username));
                info.setStringPermissions(this.userService.findPermissions(username));
            }
            RealmUtils.addRoles(info, RealmUtils.split(this.getDefaultRoles()));
            RealmUtils.addPermissions(info, RealmUtils.split(this.getDefaultPermissions()));
            return info;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
        throws AuthenticationException {
        // 增加免密登录功能，使用自定义token
        CustomToken token = (CustomToken) authcToken;

        String username = token.getUsername();
        this.logger.debug("需要认证的用户{}", username, " 类型：" + token.getType());

        String msg;
        if (StringUtils.hasText(username)) {
            if (this.userService != null) {
                User user = this.userService.findUser(username);
                if (user != null) {
                    this.logger.debug("【{}】找到用户，返回认证信息", this.getName());
                    this.logger.debug("数据库中的密码：{}", user.getPassword());
                    this.logger.debug("用户传入的密码：{}",
                            PasswordHelper.encryptPassword(String.valueOf(token.getPassword()), user.getSalt()));

                    SimpleAuthenticationInfo authenticationInfo;
                    if (!StringUtils.hasText(user.getSalt())) {
                        authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword().toCharArray(),
                            this.getName());
                    }
                    else {
                        authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword().toCharArray(),
                            ByteSource.Util.bytes(user.getSalt()), this.getName());
                    }
                    return authenticationInfo;
                }
                else {
                    String msgerror = "【" + this.getName() + "】没有找到相应的用户：" + username;
                    this.logger.error(msgerror);
                    throw new AccountException(msgerror);
                }
            }
            else {
                msg = "userService对象为空，请添加相应实现";
                this.logger.error(msg);
                throw new AuthenticationException(msg);
            }
        }
        else {
            msg = "用户名为空";
            this.logger.error(msg);
            throw new AccountException(msg);
        }
    }

    public String getDefaultRoles() {
        return this.defaultRoles;
    }

    public void setDefaultRoles(String defaultRoles) {
        this.defaultRoles = defaultRoles;
    }

    public String getDefaultPermissions() {
        return this.defaultPermissions;
    }

    public void setDefaultPermissions(String defaultPermissions) {
        this.defaultPermissions = defaultPermissions;
    }

    public UserService getUserService() {
        return this.userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


}
