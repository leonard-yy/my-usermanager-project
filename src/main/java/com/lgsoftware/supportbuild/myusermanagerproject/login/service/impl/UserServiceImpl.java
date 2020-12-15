/**************************************************************************************** 
 Copyright © 2003-2017 ZTEsoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.lgsoftware.supportbuild.myusermanagerproject.login.service.impl;

import com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro.User;
import com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <Description> <br>
 * 
 * @author dong.zhenxiang<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2017年7月13日 <br>
 * @since V1.0<br>
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * Description: <br>
     * 
     * @taskId <br>
     * @return <br>
     */
    @Override
    public Set<String> findPermissions(String username) {
        Set<String> permissions = new HashSet<String>();
        return permissions;
    }

    /**
     * Description: <br>
     * 
     * @taskId <br>
     * @return <br>
     */
    @Override
    public Set<String> findRoles(String username) {
        Set<String> rolesSet = new HashSet<String>();
//        权限代码
//        SysUser sysUser = sysUserDao.selectByUserCode(username);
//        if (sysUser != null) {
//            List<SysRole> list = sysRoleDao.qryRoleListByUserId(sysUser.getUserId());
//            if (!CollectionUtils.isEmpty(list)) {
//                rolesSet = new HashSet<String>();
//                for (SysRole bfmRole : list) {
//                    rolesSet.add(bfmRole.getRoleId().toString());
//                }
//            }
//        }

        return rolesSet;
    }

    /**
     * Description: <br>
     * 
     * @taskId <br>
     * @return <br>
     */
    @Override
    public User findUser(String username) {
//        SysUser sysUser = null;
//        try {
//            sysUser = sysUserDao.selectByUserCode(username);
//        }
//        catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        if (sysUser == null) {
//            return null;
//        }
        User user = new User();
        user.setUserId("1");
        user.setUsername("admin");
        user.setPassword("96e79218965eb72c92a549dd5a330112");
        return user;
    }

}
