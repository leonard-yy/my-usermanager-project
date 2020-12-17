package com.lgsoftware.supportbuild.myusermanagerproject.shiro;

import com.alibaba.fastjson.JSONObject;
import com.lgsoftware.supportbuild.myusermanagerproject.common.BusinessException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.View;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 该类中的方法来源于AjaxFormAuthenticationFilter（zsmartcity-shiro-3.2.3.RELEASE.jar），优化了方法isJsonView，用于判断ajax的请求 本过滤器在
 * MyShiroAutoConfiguration 中，myAuthc方法中注册使用，并添加到shiro的过滤器中。（原来使用了AjaxFormAuthenticationFilter实现方法实现）
 * 
 * @date 2020/8/3 9:06
 * @className MyShiroAjaxFormAuthenticationFilter
 * @versionInfo
 * @copyright
 */
public class MyShiroAjaxFormAuthenticationFilter extends FormAuthenticationFilter {

    private final Logger logger = LoggerFactory.getLogger(MyShiroAjaxFormAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws BusinessException {
        try {
            if (isLoginRequest(request, response)) {
                return handleLoginRequest(request, response);
            }
            else {
                return handleNoLoginRequest(request, response);
            }
        }
        catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    /**
     * 处理登录状态的请求
     * @return boolean
     */
    private boolean handleLoginRequest(ServletRequest request, ServletResponse response) throws BusinessException {
        try {
            if (isLoginSubmission(request, response)) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            }
            else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }
                return true;
            }
        }
        catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }

    }

    /**
     * 处理登录超时状态请求
     * @return boolean
     */
    private boolean handleNoLoginRequest(ServletRequest request, ServletResponse response) throws BusinessException {
        try {
            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the "
                    + "Authentication url [" + getLoginUrl() + "]");
            }
            logger.debug("未登录或登录超时");
            // 判断是否是ajax请求
            if (isJsonView(WebUtils.toHttp(request))) {
                // 是ajax请求，则在返回体中返回数据
                setContentType(request, response);
                PrintWriter out = response.getWriter();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("success", false);
                map.put("message", "未登录或登录超时");
                map.put("timeout", true);
                // 将数据写入ServletResponse
                out.print(JSONObject.toJSONString(map));
                out.flush();
                out.close();
            }
            else {
                // 不是ajax请求，则返回重定向，重定向到登录页面
                saveRequestAndRedirectToLogin(request, response);
            }
            return false;
        }
        catch (IOException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    /**
     * 判断是否是请求json数据
     *
     * @param request
     * @return
     */
    private boolean isJsonView(HttpServletRequest request) {

        // 2020-08-03 mahe 添加新的判断条件，用于标准版本的ajax请求判断
        if (request.getHeader("accept") != null
            && request.getHeader("accept").toUpperCase().contains("application/json".toUpperCase())
            && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
            return true;
        }

        // 原来版本过滤器中的判断标准
        String uri = request.getRequestURI();
        if (uri.endsWith(".json") || "json".equals(request.getParameter("format"))) {
            return true;
        }

        return false;
    }

    private void setContentType(ServletRequest request, ServletResponse response) {
        // 判断是否是IE，IE不支持 "application/json"
        String agent = WebUtils.toHttp(request).getHeader("User-Agent");
        if (agent.indexOf("MSIE") != -1 || agent.indexOf("rv:11") != -1) {
            response.setContentType("text/plain;charset=UTF-8");
        }
        else {
            response.setCharacterEncoding("UTF-8");
            MediaType mediaType = (MediaType) request.getAttribute(View.SELECTED_CONTENT_TYPE);
            if (mediaType != null && mediaType.isConcrete()) {
                response.setContentType(mediaType.toString());
            }
            else {
                response.setContentType("application/json");
            }
        }
    }
}
