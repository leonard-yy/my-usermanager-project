package com.lgsoftware.supportbuild.myusermanagerproject.common;

/**
 * 统一错误信息枚举类
 * 
 * @author zhadafei
 */
public enum ErrorEnum {

    SUCCESS(200, "成功"), SYS_EXCEPTION(500, "系统异常"), PARAM_ILLEGAL(10000001, "参数不合法"), PARAM_IS_NULL(10000002, "参数为空"),

    SIMULATION_INPUT_ILLEGAL(10000100, "请填写真实数据！");

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 错误码
     */
    private int code;

    private ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

}
