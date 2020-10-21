package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.controller.UserController;
import cn.realphago.springbootshiro.mapper.UserMapper;
import cn.realphago.springbootshiro.pojo.*;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;
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
        List<Role> roleList = roleService.findAll(null);
        String[] roles = new String[roleList.size()];
        for (Role role : roleList) {
            roles[i++] = role.getRoleNum();
        }
        try {
            userService.create(new User("admin", "admin", "超级管理员"));
            userService.create(new User("user", "user", "普通用户"));
            userService.create(new User("order", "order", "订单管理员"));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            System.out.println("UserServiceTest.create");
            System.out.println(false);
        }
    }

    @Test
    public void delete() {
        try {
            userService.delete(userService.findUserByUsername("admin").getUserNum());
            userService.delete(userService.findUserByUsername("user").getUserNum());
            userService.delete(userService.findUserByUsername("product").getUserNum());
            userService.delete(userService.findUserByUsername("order").getUserNum());
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            System.out.println("删除失败");
        }
        System.out.println("删除成功");
    }

    @Test
    public void findUserByUsername() throws InvalidParameterException {
        User admin = userService.findUserByUsername("order");
        System.out.println("admin = " + admin);
    }

    @Test
    public void mapper() throws NoSuchMethodException, ParseException {
        System.out.println(DateFormatUtils.parse("2020-09-17T00:33".replace("T", " "), "yyyy-MM-dd HH:mm"));
    }

    @Test
    public void test() {
        System.out.println(new ResultInfo<Date>(200, null));
    }

    @Test
    public void findAll() {
        PageBean<User> list = new PageBeanUtils<User>(userMapper).findList(new PageBean(0, 5), null);
        for (User element : list.getElements()) {
            System.out.println("element = " + element);
        }
    }

    @Test
    public void updateStatus() throws InvalidParameterException {
        System.out.println(userService.updateStatus(userService.findUserByUsername("user").getId(), 1));
    }

    @Test
    public void findeElementByUserNum() {
        User user = userMapper.findElementByUserNum("User202010191603119372411");
        System.out.println("user = " + user);
    }

}
