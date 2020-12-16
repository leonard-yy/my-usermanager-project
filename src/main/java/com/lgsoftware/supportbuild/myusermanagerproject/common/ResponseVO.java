package com.lgsoftware.supportbuild.myusermanagerproject.common;

/**
 * 统一返回响应实体
 */
public class ResponseVO {
    /**
     * 成功与否
     */
    private Boolean success;

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 数据
     */
    private Object data;

    public Boolean getSuccess() {
        return success;
    }

    public ResponseVO setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public ResponseVO setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public ResponseVO setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseVO setData(Object data) {
        this.data = data;
        return this;
    }
}
