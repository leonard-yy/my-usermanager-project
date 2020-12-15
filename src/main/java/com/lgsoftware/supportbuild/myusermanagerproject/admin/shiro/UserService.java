package com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro;/**
 * CREATE BY LiuG ON 2020/11/12.
 * INFO --
 */

import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * <Description> <br>
 *
 * @author LiuG<br>
 * @version 1.0<br>
 * @CreateDate 2020/11/12 <br>
 * @see com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro <br>
 * @since V1.0<br>
 */
@Service
public interface UserService {
    Set<String> findRoles(String username);

    Set<String> findPermissions(String username);

    User findUser(String username);
}
