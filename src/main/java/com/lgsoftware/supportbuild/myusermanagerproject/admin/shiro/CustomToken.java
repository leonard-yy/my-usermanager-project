package com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @program: psmp-fx
 * @description: CustomeToken
 * @author: Mr.Wang
 * @create: 2020-03-04 09:55
 **/
public class CustomToken extends UsernamePasswordToken {
    private static final long serialVersionUID = -2564928913725078138L;

    private LoginType type;


    public CustomToken() {
        super();
    }

    public CustomToken(String username, String password, LoginType type, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
        this.type = type;
    }

    /**
     * 免密登录
     */
    public CustomToken(String username) {
        super(username, "", false, null);
        this.type = LoginType.NOPASSWD;
    }

    /**
     * 账号密码登录
     */
    public CustomToken(String username, String password) {
        super(username, password, false, null);
        this.type = LoginType.PASSWORD;
    }

    public LoginType getType() {
        return type;
    }


    public void setType(LoginType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName());
        sb.append(" - ");
        sb.append(type);
        return sb.toString();
    }
}
