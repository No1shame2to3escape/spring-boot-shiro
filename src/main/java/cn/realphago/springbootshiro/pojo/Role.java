package cn.realphago.springbootshiro.pojo;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/08/2020/8/7 8:34
 */
public class Role implements Serializable {

    private String id;//角色ID
    private String roleNum;//角色编号
    private String name;//角色名称
    private String roleDesc;//角色描述
    private List<Permission> permissionList;
    private Date gmtCreate;
    private String str_gmtCreate;
    private Date gmtModified;
    private String str_gmtModified;

    public Role() {
    }

    public Role(String name, String roleDesc) {
        this(UUID.randomUUID().toString().replace("-", ""),
                DateFormatUtils.getOrderNum("Role"),
                name,
                roleDesc);
    }

    public Role(String id, String roleNum, String name, String roleDesc) {
        this.id = id;
        this.roleNum = roleNum;
        this.name = name;
        this.roleDesc = roleDesc;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", roleNum='" + roleNum + '\'' +
                ", name='" + name + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", permissionList=" + permissionList +
                ", gmtCreate=" + gmtCreate +
                ", str_gmtCreate='" + str_gmtCreate + '\'' +
                ", gmtModified=" + gmtModified +
                ", str_gmtModified='" + str_gmtModified + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Role) {
            return this.id.equals(((Role) obj).getId());
        }
        return false;
    }

    public boolean hasPermission(Permission permission) {
        boolean flag = false;
        for (Permission permission1 : permissionList) {
            if (permission1.equals(permission)) {
                flag = true;
            }
        }
        return flag;
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

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public String getRoleNum() {
        return roleNum;
    }

    public void setRoleNum(String roleNum) {
        this.roleNum = roleNum;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
