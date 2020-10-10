package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.Permission;
import cn.realphago.springbootshiro.pojo.Role;

import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/14 15:06
 */
public interface PermissionService extends PageBeanService<Permission> {

    boolean create(Permission permission);

    boolean delete(Permission permission);

    boolean update(Permission permission);

    Permission findPermissionById(String id);

    boolean authorize(Role role, Permission permission);

    Permission findPermissionByName(String name);

    boolean deAuthorizeByRoleNum(String roleNum);

    boolean deAuthorizeByPermissionNum(String permissionNum);

    List<Permission> findAll(String name);

}
