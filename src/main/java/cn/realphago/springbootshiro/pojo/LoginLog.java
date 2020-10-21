package cn.realphago.springbootshiro.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/11 2:37
 */
public class LoginLog implements Serializable {

    private String id; //主键
    private Date loginTime; //登录时间
    private String username;    //用户名

    public LoginLog() {
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "id='" + id + '\'' +
                ", loginTime=" + loginTime +
                ", username='" + username + '\'' +
                '}';
    }

    public LoginLog(String id, Date loginTime, String username) {
        this.id = id;
        this.loginTime = loginTime;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
