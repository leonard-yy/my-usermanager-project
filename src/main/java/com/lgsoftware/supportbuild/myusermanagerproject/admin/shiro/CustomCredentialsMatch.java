package com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author LiuG
 * @Description // CustomCredentialsMatch
 * @Date 2020/11/12
 * @Param
 * @return
 * @Exception
 **/
public class CustomCredentialsMatch extends HashedCredentialsMatcher {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //增加免密登录功能，使用自定义token
        CustomToken usertoken = (CustomToken) token;

        //免密登录,不验证密码
        if (LoginType.NOPASSWD.equals(usertoken.getType())) {
            logger.debug("免密登录,不验证密码! LoginType:" + LoginType.NOPASSWD);
            return true;
        }

        boolean matches = super.doCredentialsMatch(token, info);
        return matches;
    }
}

