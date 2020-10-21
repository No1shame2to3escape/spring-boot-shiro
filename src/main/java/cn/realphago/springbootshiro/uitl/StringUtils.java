package cn.realphago.springbootshiro.uitl;

import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/16 1:52
 */
public class StringUtils {

    public static String getCommonUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String handleSpacingAndShift(String oldString) {
        if (isEmpty(oldString)) return null;
        return oldString.trim().replaceAll(" ", "").replaceAll("\n", "");
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) return true;
        if (obj instanceof String) {
            return "".equals(((String) obj).trim().replaceAll(" ", ""));
        }
        return false;
    }

}
