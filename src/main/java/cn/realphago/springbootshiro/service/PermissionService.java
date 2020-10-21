package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.Permission;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;

import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/14 15:06
 */
public interface PermissionService extends PageBeanService<Permission> {

    boolean create(Permission permission) throws InvalidParameterException;

    boolean delete(String id) throws InvalidParameterException;

    boolean update(Permission permission) throws InvalidParameterException;

    Permission findPermissionById(String id) throws InvalidParameterException;

    boolean authorize(String roleNum, String permissionNum) throws InvalidParameterException;

    Permission findPermissionByName(String name) throws InvalidParameterException;

    boolean deAuthorizeByRoleNum(String roleNum) throws InvalidParameterException;

    boolean deAuthorizeByPermissionNum(String permissionNum) throws InvalidParameterException;

    boolean deleteAll();

    List<Permission> findElementLikeUrl(String url) throws InvalidParameterException;

    boolean authorizePermissions(String roleNum, String[] permissionNums) throws InvalidParameterException;

}
