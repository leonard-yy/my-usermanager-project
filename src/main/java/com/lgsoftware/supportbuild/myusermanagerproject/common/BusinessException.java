package com.lgsoftware.supportbuild.myusermanagerproject.common;

/**
 * 统一异常处理类
 *
 * @author LiuG
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    public BusinessException() {
        super();
    }

    public BusinessException(String mes, Throwable cause) {

        super(mes, cause);
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BusinessException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(ErrorEnum errorEnum) {
        super(errorEnum.getMsg());
        this.errorCode = errorEnum.getCode();
        this.errorMsg = errorEnum.getMsg();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public BusinessException setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public BusinessException setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }
}
