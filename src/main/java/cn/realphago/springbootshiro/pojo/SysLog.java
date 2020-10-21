package cn.realphago.springbootshiro.pojo;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/16 14:55
 */
public class SysLog implements Serializable {

    private String id;
    private Date visitTime;
    private String str_visitTime;
    private String username;
    private String ip;
    private String url;
    private Long executionTime;
    private String method;

    public SysLog() {
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id='" + id + '\'' +
                ", visitTime=" + visitTime +
                ", str_visitTime='" + str_visitTime + '\'' +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", executionTime=" + executionTime +
                ", method='" + method + '\'' +
                '}';
    }

    public String getStr_visitTime() {
        if (visitTime != null) {
            return DateFormatUtils.format(visitTime);
        }
        return "未知";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
