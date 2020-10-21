package cn.realphago.springbootshiro.pojo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/10 23:48
 */
public class UserStatistics implements Serializable {

    private Integer userCount;  //用户数量统计
    private Integer roleCount;  //角色数量统计
    private Integer loginCount;   //登录数量统计
    private Integer operationCount;  //操作数量统计
    private Integer onlineCount;    //在线人数统计
    private Integer[] weekLoginCount;   //近7天登录次数统计
    private String[] weekDateStrings;

    public UserStatistics() {
    }

    @Override
    public String toString() {
        return "UserStatistics{" +
                "userCount=" + userCount +
                ", roleCount=" + roleCount +
                ", loginCount=" + loginCount +
                ", operationCount=" + operationCount +
                ", onlineCount=" + onlineCount +
                ", weekLoginCount=" + Arrays.toString(weekLoginCount) +
                ", weekDateStrings=" + Arrays.toString(weekDateStrings) +
                '}';
    }

    public UserStatistics(Integer userCount, Integer roleCount, Integer loginCount, Integer operationCount, Integer onlineCount, Integer[] weekLoginCount, String[] weekDateStrings) {
        this.userCount = userCount;
        this.roleCount = roleCount;
        this.loginCount = loginCount;
        this.operationCount = operationCount;
        this.onlineCount = onlineCount;
        this.weekLoginCount = weekLoginCount;
        this.weekDateStrings = weekDateStrings;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getRoleCount() {
        return roleCount;
    }

    public void setRoleCount(Integer roleCount) {
        this.roleCount = roleCount;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Integer getOperationCount() {
        return operationCount;
    }

    public void setOperationCount(Integer operationCount) {
        this.operationCount = operationCount;
    }

    public Integer getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(Integer onlineCount) {
        this.onlineCount = onlineCount;
    }

    public Integer[] getWeekLoginCount() {
        return weekLoginCount;
    }

    public void setWeekLoginCount(Integer[] weekLoginCount) {
        this.weekLoginCount = weekLoginCount;
    }

    public String[] getWeekDateStrings() {
        return weekDateStrings;
    }

    public void setWeekDateStrings(String[] weekDateStrings) {
        this.weekDateStrings = weekDateStrings;
    }
}
