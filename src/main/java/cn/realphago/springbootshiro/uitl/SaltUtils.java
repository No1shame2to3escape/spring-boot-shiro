package cn.realphago.springbootshiro.uitl;

import java.util.Random;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 13:13
 */
public class SaltUtils {

    private final static char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()".toCharArray();

    public static String getSalt(int num) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < num; i++) {
            char ch = chars[random.nextInt(chars.length)];
            stringBuffer.append(ch);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(SaltUtils.getSalt(8));
    }

}
