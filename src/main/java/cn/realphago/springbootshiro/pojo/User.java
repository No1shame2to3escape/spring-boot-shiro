package cn.realphago.springbootshiro.pojo;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/6 13:54
 */
public class User implements Serializable {

    private String id;
    private String userNum;
    private String username;
    private String password;
    private Integer gender;
    private String str_gender;
    private String name;
    private String salt;
    private Integer status;
    private String str_status;
    private List<Role> roleList;
    private Date gmtCreate;
    private String str_gmtCreate;
    private Date gmtModified;
    private String str_gmtModified;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userNum='" + userNum + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", str_gender='" + str_gender + '\'' +
                ", name='" + name + '\'' +
                ", salt='" + salt + '\'' +
                ", status=" + status +
                ", str_status='" + str_status + '\'' +
                ", roleList=" + roleList +
                ", gmtCreate=" + gmtCreate +
                ", str_gmtCreate='" + str_gmtCreate + '\'' +
                ", gmtModified=" + gmtModified +
                ", str_gmtModified='" + str_gmtModified + '\'' +
                '}';
    }

    public User(String username, String password, String name, String salt, Integer status) {
        this(UUID.randomUUID().toString(),
                DateFormatUtils.getOrderNum("User"),
                username,
                password,
                1,
                name,
                salt,
                status);
    }

    public User(String username, String password, String name) {
        this(username,
                password,
                name,
                null,
                1);
    }

    public User(String id, String userNum, String username, String password, Integer gender, String name, String salt, Integer status) {
        this.id = id;
        this.userNum = userNum;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.name = name;
        this.salt = salt;
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getStr_status() {
        if (status != null) {
            return status == 0 ? "关闭" : "开启";
        }
        return "未知";
    }

    public String getStr_gmtCreate() {
        if (gmtCreate != null) {
            return DateFormatUtils.format(gmtCreate);
        }
        return "未知";
    }

    public String getStr_gmtModified() {
        if (gmtModified != null) {
            return DateFormatUtils.format(gmtModified);
        }
        return "未知";
    }

    public boolean hasRole(Role role) {
        boolean flag = false;
        for (Role role1 : roleList) {
            if (role1.equals(role)) {
                flag = true;
            }
        }
        return flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStr_gender() {
        if (gender != null) {
            return gender == 0 ? "女" : "男";
        }
        return "未知";
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getUsername() {
        return username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
