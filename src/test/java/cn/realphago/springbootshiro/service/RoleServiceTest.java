package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.Permission;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/10 14:25
 */
@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Test
    public void create() {
        try {
            roleService.create(new Role("admin", "超级管理员"));
            roleService.create(new Role("user", "普通用户"));
            roleService.create(new Role("product", "产品管理员"));
            roleService.create(new Role("order", "订单管理员"));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
        System.out.println("RoleServiceTest.create");
        System.out.println("添加成功");
    }

    @Test
    public void delete() {
        try {
            roleService.delete(roleService.findRoleByName("admin").getRoleNum());
            roleService.delete(roleService.findRoleByName("user").getRoleNum());
            roleService.delete(roleService.findRoleByName("product").getRoleNum());
            roleService.delete(roleService.findRoleByName("order").getRoleNum());
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
        System.out.println("RoleServiceTest.delete");
        System.out.println("删除成功");
    }

    @Test
    public void findAll() {
        List<Role> roleList = roleService.findAll(null);
        for (Role role : roleList) {
            System.out.println("role = " + role);
        }
    }

    @Test
    public void hasPermission() throws InvalidParameterException {
        Role role = roleService.findRoleByName("admin");
        System.out.println("role = " + role);
        Role role1 = roleService.findElementById(role.getId());
        System.out.println("role1 = " + role1);
        Permission permission = permissionService.findPermissionByName("user操作删除权限");
        System.out.println("permission = " + permission);
        boolean flag = role.hasPermission(permission);
        System.out.println(role1.hasPermission(permission));
    }

    @Test
    public void deAuthorizeByRoleNum() throws InvalidParameterException {
        boolean flag = roleService.delete("f83f63eadce3454f9c6fdd07dc341d52");
        System.out.println("flag = " + flag);
    }

}
