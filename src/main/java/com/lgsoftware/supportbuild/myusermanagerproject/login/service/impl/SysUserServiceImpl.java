package com.lgsoftware.supportbuild.myusermanagerproject.login.service.impl;/**
 * CREATE BY LiuG ON 2020/11/12.
 * INFO --
 */

import com.lgsoftware.supportbuild.myusermanagerproject.login.dao.SysUserMapper;
import com.lgsoftware.supportbuild.myusermanagerproject.login.entity.SysUser;
import com.lgsoftware.supportbuild.myusermanagerproject.login.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <Description> <br>
 *
 * @author LiuG<br>
 * @version 1.0<br>
 * @CreateDate 2020/11/12 <br>
 * @see com.lgsoftware.supportbuild.myusermanagerproject.login.service.impl <br>
 * @since V1.0<br>
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper mapper;

    @Override
    public SysUser selectUserByUserCode(String loginName) {
        return mapper.selectByUserCode(loginName);
    }
}
