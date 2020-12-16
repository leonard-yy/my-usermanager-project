package com.lgsoftware.supportbuild.myusermanagerproject.util;

import com.lgsoftware.supportbuild.myusermanagerproject.common.BusinessException;
import com.lgsoftware.supportbuild.myusermanagerproject.common.ErrorEnum;
import com.lgsoftware.supportbuild.myusermanagerproject.common.ResponseVO;

/**
 * 统一错误信息工具类
 *
 * @author zhadafei
 */
public final class ResponseVOUtils {

    private ResponseVOUtils() {
    }

    /**
     * 构建成功返回
     *
     * @param data
     * @return
     */
    public static ResponseVO buildSuccess(Object data) {
        ResponseVO responseVo = new ResponseVO();
        responseVo.setSuccess(Boolean.TRUE);
        responseVo.setErrorCode(ErrorEnum.SUCCESS.getCode());
        responseVo.setErrorMsg(ErrorEnum.SUCCESS.getMsg());
        responseVo.setData(data);
        return responseVo;
    }

    /**
     * 根据自定义错误信息构建错误
     *
     * @param errorCode
     * @param errorMsg
     * @return
     */
    public static ResponseVO buildError(Integer errorCode, String errorMsg) {
        ResponseVO responseVo = new ResponseVO();
        responseVo.setSuccess(Boolean.FALSE);
        responseVo.setErrorCode(errorCode);
        responseVo.setErrorMsg(errorMsg);
        return responseVo;
    }

    public static ResponseVO buildErrorByEnum(ErrorEnum errorEnum) {
        ResponseVO responseVo = new ResponseVO();
        responseVo.setSuccess(Boolean.FALSE);
        responseVo.setErrorCode(errorEnum.getCode());
        responseVo.setErrorMsg(errorEnum.getMsg());
        return responseVo;
    }

    public static ResponseVO buildErrorByException(BusinessException be) {
        ResponseVO responseVo = new ResponseVO();
        responseVo.setSuccess(Boolean.FALSE);
        responseVo.setErrorCode(be.getErrorCode());
        responseVo.setErrorMsg(be.getErrorMsg());
        return responseVo;
    }
}
