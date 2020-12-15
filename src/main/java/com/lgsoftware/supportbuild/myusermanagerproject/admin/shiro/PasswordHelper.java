package com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro;/**
 * CREATE BY LiuG ON 2020/11/12.
 * INFO --
 */

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

/**
 * <Description> <br>
 *
 * @author LiuG<br>
 * @version 1.0<br>
 * @CreateDate 2020/11/12 <br>
 * @see com.lgsoftware.supportbuild.myusermanagerproject.admin.shiro <br>
 * @since V1.0<br>
 */
public class PasswordHelper {
    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private static String hashAlgorithmName = "md5";

    private static int hashIterations = 1;

    private static boolean base64Coded = false;

    public PasswordHelper() {
    }

    public static User encryptPassword(User user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = encryptPassword(user.getPassword(), user.getSalt());
        user.setPassword(newPassword);
        return user;
    }

    public static String encryptPassword(String password) {
        return encryptPassword(password, (String)null);
    }

    public static String encryptPassword(String password, String salt) {
        byte[] source = null;
        ByteSource bytes = ByteSource.Util.bytes(password);
        if (base64Coded) {
            source = Base64.encode(bytes.getBytes());
        } else {
            source = bytes.getBytes();
        }

        return StringUtils.isEmpty(salt) ? (new SimpleHash(hashAlgorithmName, source, (Object)null, hashIterations)).toHex() : (new SimpleHash(hashAlgorithmName, source, ByteSource.Util.bytes(salt), hashIterations)).toHex();
    }

    protected static void setHashAlgorithmName(String algorithmName) {
        hashAlgorithmName = algorithmName;
    }

    protected static void setHashIterations(int hashIterations) {
        PasswordHelper.hashIterations = hashIterations;
    }

    protected static void setBase64Coded(boolean base64Coded) {
        PasswordHelper.base64Coded = base64Coded;
    }
}
