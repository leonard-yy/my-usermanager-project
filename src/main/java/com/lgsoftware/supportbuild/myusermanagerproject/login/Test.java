package com.lgsoftware.supportbuild.myusermanagerproject.login;/**
 * CREATE BY LiuG ON 2020/11/18.
 * INFO --
 */

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * <Description> <br>
 *
 * @author LiuG<br>
 * @version 1.0<br>
 * @CreateDate 2020/11/18 <br>
 * @see com.lgsoftware.supportbuild.myusermanagerproject.login <br>
 * @since V1.0<br>
 */
public class Test {

    public static void main(String[] args) {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = "111111";//密码原值
        Object salt = null;//盐值
        int hashIterations = 1;//加密1024次
        String result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations).toBase64();
        System.out.println(result);
    }
}
