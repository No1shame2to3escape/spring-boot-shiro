package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.Role;
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

    @Test
    public void create() {
        roleService.create(new Role("admin", "超级管理员"));
        roleService.create(new Role("user", "普通用户"));
        roleService.create(new Role("product", "产品管理员"));
        roleService.create(new Role("order", "订单管理员"));
    }

    @Test
    public void delete() {
        roleService.delete(roleService.findRoleByName("admin"));
        roleService.delete(roleService.findRoleByName("user"));
        roleService.delete(roleService.findRoleByName("product"));
        roleService.delete(roleService.findRoleByName("order"));
    }

    @Test
    public void findAll() {
        List<Role> roleList = roleService.findAll();
        for (Role role : roleList) {
            System.out.println("role = " + role);
        }
    }

}
