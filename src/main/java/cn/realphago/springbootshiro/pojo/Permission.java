package cn.realphago.springbootshiro.pojo;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 11:46
 */
public class Permission implements Serializable {

    private String id;
    private String permissionNum;
    private String name;
    private String url;
    private Date gmtCreate;
    private String str_gmtCreate;
    private Date gmtModified;
    private String str_gmtModified;

    public Permission() {
    }

    public Permission(String name, String url) {
        this(UUID.randomUUID().toString().replace("-", ""),
                name,
                url,
                DateFormatUtils.getOrderNum("Perm"));
    }

    public Permission(String id, String name, String url, String permissionNum) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.permissionNum = permissionNum;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id='" + id + '\'' +
                ", permissionNum='" + permissionNum + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", gmt_create=" + gmtCreate +
                ", str_gmt_create='" + str_gmtCreate + '\'' +
                ", gmt_modified=" + gmtModified +
                ", str_gmt_modified='" + str_gmtModified + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Permission) {
            return this.id.equals(((Permission) obj).getId());
        }
        return false;
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

    public String getPermissionNum() {
        return permissionNum;
    }

    public void setPermissionNum(String permissionNum) {
        this.permissionNum = permissionNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return name;
    }

    public void setPermissionName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
