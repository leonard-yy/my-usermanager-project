package com.lgsoftware.supportbuild.myusermanagerproject.login.service;/**
 * CREATE BY LiuG ON 2020/11/12.
 * INFO --
 */

import com.lgsoftware.supportbuild.myusermanagerproject.login.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * <Description> 系统用户服务<br>
 *
 * @author LiuG<br>
 * @version 1.0<br>
 * @CreateDate 2020/11/12 <br>
 * @see com.lgsoftware.supportbuild.myusermanagerproject.login.service <br>
 * @since V1.0<br>
 */
@Service
public interface SysUserService {

    SysUser selectUserByUserCode(String loginName);
}
