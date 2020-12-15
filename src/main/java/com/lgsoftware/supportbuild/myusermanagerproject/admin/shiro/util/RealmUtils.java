package com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro.util;/**
 * CREATE BY LiuG ON 2020/11/12.
 * INFO --
 */

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <Description> <br>
 *
 * @author LiuG<br>
 * @version 1.0<br>
 * @CreateDate 2020/11/12 <br>
 * @see com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro.util <br>
 * @since V1.0<br>
 */
public class RealmUtils {

    public RealmUtils() {
    }

    public static List<String> split(String s) {
        List<String> list = new ArrayList();
        String[] elements = StringUtils.split(s, ',');
        if (elements != null && elements.length > 0) {
            String[] var3 = elements;
            int var4 = elements.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String element = var3[var5];
                if (StringUtils.hasText(element)) {
                    list.add(element.trim());
                }
            }
        }

        return list;
    }

    public static void addRoles(SimpleAuthorizationInfo simpleAuthorizationInfo, List<String> roles) {
        Iterator var2 = roles.iterator();

        while(var2.hasNext()) {
            String role = (String)var2.next();
            simpleAuthorizationInfo.addRole(role);
        }

    }

    public static void addPermissions(SimpleAuthorizationInfo simpleAuthorizationInfo, List<String> permissions) {
        Iterator var2 = permissions.iterator();

        while(var2.hasNext()) {
            String permission = (String)var2.next();
            simpleAuthorizationInfo.addStringPermission(permission);
        }

    }
}
