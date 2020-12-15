package com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro;

/**
 * @program: psmp-fx
 * @description: LoginType
 * @author: Mr.Wang
 * @create: 2020-03-04 09:56
 **/

/**
 * 登录类型
 */
public enum LoginType {
    // 密码登录
    PASSWORD("password"),
    // 免密登录
    NOPASSWD("nopassword");
    // 状态值
    private String code;

    private LoginType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
