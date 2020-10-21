package cn.realphago.springbootshiro.uitl;

import org.apache.shiro.web.session.HttpServletSession;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/11 1:04
 */
public class GlobalInfoUtils {

    private static Integer onlineCount = 0; //在线人数

    private static Map<HttpSession, Date> userLastOperationTimeMap = new HashMap<HttpSession, Date>();

    public static void login() {
        setOnlineCount(onlineCount + 1);
    }

    public static void logout() {
        setOnlineCount(onlineCount - 1);
    }

    public static Integer getOnlineCount() {
        return onlineCount;
    }

    private static void setOnlineCount(Integer onlineCount) {
        if (onlineCount >= 0) {
            GlobalInfoUtils.onlineCount = onlineCount;
        }
    }

    public static void removeOperationMap(HttpSession servletSession) {
        userLastOperationTimeMap.remove(servletSession);
        setOnlineCount(onlineCount - 1);
    }

    public static void updateOperationMap(HttpSession servletSession, Date date) {
        if (userLastOperationTimeMap.containsKey(servletSession)) {
            userLastOperationTimeMap.replace(servletSession, date);
        } else {
            userLastOperationTimeMap.put(servletSession, date);
            setOnlineCount(onlineCount + 1);
        }
    }

    public static Map<HttpSession, Date> getUserLastOperationTimeMap() {
        return userLastOperationTimeMap;
    }
}
