package cn.realphago.springbootshiro.pojo;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/08/2020/8/7 8:34
 */
public class Role {

    private String id;//角色ID
    private String roleNum;//角色编号
    private String name;//角色名称
    private String roleDesc;//角色描述
    private List<Permission> permissionList;
    private Date gmt_create;
    private String str_gmt_create;
    private Date gmt_modified;
    private String str_gmt_modified;

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
                ", gmt_create=" + gmt_create +
                ", str_gmt_create='" + str_gmt_create + '\'' +
                ", gmt_modified=" + gmt_modified +
                ", str_gmt_modified='" + str_gmt_modified + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Role) {
            return this.id.equals(((Role) obj).getId());
        }
        return false;
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
