package cn.realphago.springbootshiro.pojo;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 11:46
 */
public class Permission {

    private String id;
    private String permissionNum;
    private String name;
    private String url;
    private Date gmt_create;
    private String str_gmt_create;
    private Date gmt_modified;
    private String str_gmt_modified;

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
                ", gmt_create=" + gmt_create +
                ", str_gmt_create='" + str_gmt_create + '\'' +
                ", gmt_modified=" + gmt_modified +
                ", str_gmt_modified='" + str_gmt_modified + '\'' +
                '}';
    }

    public String getStr_gmt_create() {
        if (gmt_create != null) {
            return DateFormatUtils.format(gmt_create);
        }
        return "未知";
    }

    public String getStr_gmt_modified() {
        if (gmt_modified != null) {
            return DateFormatUtils.format(gmt_modified);
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

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Date gmt_modified) {
        this.gmt_modified = gmt_modified;
    }
}
