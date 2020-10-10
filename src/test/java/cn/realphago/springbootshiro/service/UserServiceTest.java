package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.controller.UserController;
import cn.realphago.springbootshiro.mapper.UserMapper;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/9 15:55
 */
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserMapper userMapper;

    @Test
    public void create() {
        int i = 0;
        List<Role> roleList = roleService.findAll();
        String[] roles = new String[roleList.size()];
        for (Role role : roleList) {
            roles[i++] = role.getRoleNum();
        }
        userService.create(new User("admin", "admin", "超级管理员"), roles);
        userService.create(new User("user", "user", "普通用户"), new String[]{roleService.findRoleByName("user").getRoleNum()});
        userService.create(new User("product", "product", "产品管理员"), new String[]{roleService.findRoleByName("user").getRoleNum(), roleService.findRoleByName("product").getRoleNum()});
        userService.create(new User("order", "order", "订单管理员"), new String[]{roleService.findRoleByName("user").getRoleNum(), roleService.findRoleByName("order").getRoleNum()});
    }

    @Test
    public void create1() {
        userService.create(new User("guest", "guest", "游客"));
    }

    @Test
    public void update() {
        User user = userService.findUserByUsername("user");
        user.setName("游客");
        userService.update(user);
    }

    @Test
    public void delete() {
        userService.delete(userService.findUserByUsername("admin"));
        userService.delete(userService.findUserByUsername("user"));
        userService.delete(userService.findUserByUsername("product"));
        userService.delete(userService.findUserByUsername("order"));
    }

    @Test
    public void findUserByUsername() {
        User admin = userService.findUserByUsername("order");
        System.out.println("admin = " + admin);
    }

    @Test
    public void mapper() throws NoSuchMethodException, ParseException {
        System.out.println(DateFormatUtils.parse("2020-09-17T00:33".replace("T", " "), "yyyy-MM-dd HH:mm"));
    }

}
