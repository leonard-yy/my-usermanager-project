/**
 * CREATE BY LiuG ON 2020/11/12.
 * INFO --
 */
package com.lgsoftware.supportbuild.myusermanagerproject.login.controller;

import com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro.CustomToken;
import com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro.LoginType;
import com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro.User;
import com.lgsoftware.supportbuild.myusermanagerproject.common.ResponseVO;
import com.lgsoftware.supportbuild.myusermanagerproject.util.ResponseVOUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;

/**
 * <Description> User Login<br>
 *
 * @author LiuG<br>
 * @version 1.0<br>
 * @date 2020/11/12 <br>
 * @see com.lgsoftware.supportbuild.myusermanagerproject.login <br>
 * @since V1.0<br>
 */
@RestController
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/dologin.html", method = RequestMethod.POST)
    public ResponseVO doLogin(HttpServletRequest request, @RequestBody User user) {
        logger.debug("LoginController is login start");
        String loginName = user.getUsername();
        String password = user.getPassword();
        boolean isRememberMe = user.isRememberMe();

        CustomToken token = new CustomToken(loginName, password, LoginType.PASSWORD, isRememberMe, null);
        // 登录提示信息
        String msg = null;
        // 执行登录
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        }
        catch (AuthenticationException e) {
            request.setAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, e.getClass().getName());
            logger.warn(e.getMessage());
        }

        // 判断登录是否成功
        if (currentUser.isAuthenticated()) {
            // 记录登录时间
            Date loginTime = Calendar.getInstance().getTime();
            return ResponseVOUtils.buildSuccess(currentUser);
        }
        else {
            String exceptionClassName = (String) request
                    .getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
            HttpSession httpSession = request.getSession(true);
            httpSession.setAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME, exceptionClassName);

            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                // 您输入的用户名无效
                msg = "用户不存在！";
            }
            else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                // 密码错误
                msg = "用户名或密码错误！";
            }
            else if (AuthenticationException.class.getName().equals(exceptionClassName)) {
                msg = "用户名或密码错误！";
            }
            else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
                msg = "账号被锁定！";
            }
            else if (AccountException.class.getName().equals(exceptionClassName)) {
                msg = "用户不存在！";
            }
            else if (exceptionClassName != null) {
                msg = "其他错误：" + exceptionClassName;
            }
            logger.debug(msg);
            return ResponseVOUtils.buildError(-1, msg);
        }
    }

}
