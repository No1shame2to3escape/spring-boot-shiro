package cn.realphago.springbootshiro.uitl;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 13:03
 */
public class MD5Utils {

    private static Md5Hash md5Hash;

    private static void setMd5Hash(String source, String salt) {
        MD5Utils.md5Hash = new Md5Hash(source, salt);
    }

    private static void setMd5Hash(String source, String salt, int hashIterations) {
        MD5Utils.md5Hash = new Md5Hash(source, salt, hashIterations);
    }

    private static void setMd5Hash(String source) {
        MD5Utils.md5Hash = new Md5Hash(source);
    }

    public static String toHex(String source) {
        setMd5Hash(source);
        return md5Hash.toHex();
    }

    public static String toHex(String source, String salt) {
        setMd5Hash(source, salt);
        return md5Hash.toHex();
    }

    public static String toHex(String source, String salt, int hashIterations) {
        setMd5Hash(source, salt, hashIterations);
        return md5Hash.toHex();
    }


}
