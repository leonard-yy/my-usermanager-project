package com.lgsoftware.supportbuild.myusermanagerproject.admin;/**
 * CREATE BY LiuG ON 2020/11/12.
 * INFO --
 */

import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;

import java.util.LinkedHashMap;

/**
 * <Description> shiro认证配置类<br>
 *
 * @author LiuG<br>
 * @version 1.0<br>
 * @CreateDate 2020/11/12 <br>
 * @see com.lgsoftware.supportbuild.myusermanagerproject.admin <br>
 * @since V1.0<br>
 */
public class AdminShiroFilterChainCustomizer implements ShiroFilterChainDefinition {

    @Override
    public LinkedHashMap<String, String> getFilterChainMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        map.put("/dologin**", "anon");
        map.put("/index.html", "authc");
        map.put("/**/**", "authc");

        return map;
    }
}
