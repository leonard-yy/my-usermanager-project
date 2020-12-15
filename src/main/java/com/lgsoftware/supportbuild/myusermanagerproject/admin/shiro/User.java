package com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro;/**
 * CREATE BY LiuG ON 2020/11/12.
 * INFO --
 */

import java.io.Serializable;

/**
 * <Description> 用户<br>
 *
 * @author LiuG<br>
 * @version 1.0<br>
 * @CreateDate 2020/11/12 <br>
 * @see com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro.vo <br>
 * @since V1.0<br>
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String username;
    private String password;
    private String salt;

    public User() {
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            User user = (User)o;
            if (this.userId != null) {
                if (!this.userId.equals(user.userId)) {
                    return false;
                }
            } else if (user.userId != null) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.userId != null ? this.userId.hashCode() : 0;
    }

    public static enum LoginType {
        WEB,
        APP;

        private LoginType() {
        }
    }

    public static enum Type {
        staff,
        pubUser;

        private Type() {
        }
    }
}
