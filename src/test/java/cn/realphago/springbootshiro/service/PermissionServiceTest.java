package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.mapper.PermissionMapper;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Permission;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/14 15:27
 */
@SpringBootTest
public class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @Test
    public void create() {
        try {
            permissionService.create(new Permission("user操作全部权限", "user:*:*"));
            permissionService.create(new Permission("user操作查询权限", "user:find:*"));
            permissionService.create(new Permission("user操作添加权限", "user:create:*"));
            permissionService.create(new Permission("user操作删除权限", "user:delete:*"));
            permissionService.create(new Permission("user操作更新权限", "user:update:*"));
            permissionService.create(new Permission("role操作全部权限", "role:*:*"));
            permissionService.create(new Permission("role操作查询权限", "role:find:*"));
            permissionService.create(new Permission("role操作添加权限", "role:create:*"));
            permissionService.create(new Permission("role操作删除权限", "role:delete:*"));
            permissionService.create(new Permission("role操作更新权限", "role:update:*"));
            permissionService.create(new Permission("product操作全部权限", "product:*:*"));
            permissionService.create(new Permission("product操作查询权限", "product:find:*"));
            permissionService.create(new Permission("product操作添加权限", "product:create:*"));
            permissionService.create(new Permission("product操作删除权限", "product:delete:*"));
            permissionService.create(new Permission("product操作更新权限", "product:update:*"));
            permissionService.create(new Permission("order操作全部权限", "order:*:*"));
            permissionService.create(new Permission("order操作查询权限", "order:find:*"));
            permissionService.create(new Permission("order操作添加权限", "order:create:*"));
            permissionService.create(new Permission("order操作删除权限", "order:delete:*"));
            permissionService.create(new Permission("order操作更新权限", "order:update:*"));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTest() {
        try {
            permissionService.create(new Permission("123456789123456789123456789", "user:*:*"));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findList() {
        PageBean<Permission> all = new PageBeanUtils<Permission>().findAll(1, 5, permissionService, null);
        System.out.println("all = " + all);
    }

    @Test
    public void authorize() {
        List<Permission> permissionList = permissionService.findList(new PageBean(0, 100),
                null)
                .getElements();
        for (Permission permission : permissionList) {
            try {
                permissionService.authorize(roleService.findRoleByName("admin").getRoleNum(), permission.getPermissionNum());
            } catch (InvalidParameterException e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> parmMap = new HashMap<String, Object>();
        parmMap.put("name", "product");
        permissionList = permissionService.findList(new PageBean(0, 100),
                parmMap)
                .getElements();
        for (Permission permission : permissionList) {
            try {
                permissionService.authorize(roleService.findRoleByName("product").getRoleNum(), permission.getPermissionNum());
            } catch (InvalidParameterException e) {
                e.printStackTrace();
            }
        }
        parmMap.replace("name", "order");
        permissionList = permissionService.findList(new PageBean(0, 100),
                parmMap)
                .getElements();
        for (Permission permission : permissionList) {
            try {
                permissionService.authorize(roleService.findRoleByName("order").getRoleNum(), permission.getPermissionNum());
            } catch (InvalidParameterException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void deAuthorizeByRoleNum() {
        try {
            permissionService.deAuthorizeByRoleNum(roleService.findRoleByName("admin").getRoleNum());
            permissionService.deAuthorizeByRoleNum(roleService.findRoleByName("product").getRoleNum());
            permissionService.deAuthorizeByRoleNum(roleService.findRoleByName("order").getRoleNum());
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        Permission permission = new Permission("user操作全部权限", "user:*:*");
        try {
            permission.setId(permissionService.findPermissionByName("user操作全部权限a ").getId());
            boolean flag = permissionService.update(permission);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAll() {
        Map<String, Object> parmMap = new HashMap<String, Object>();
        parmMap.put("name", "product");
        List<Permission> permissionList = permissionService.findList(new PageBean(1, 5), parmMap)
                .getElements();
        for (Permission permission : permissionList) {
            System.out.println("permission = " + permission);
        }
    }

}
