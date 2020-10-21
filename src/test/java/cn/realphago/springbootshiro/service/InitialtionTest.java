package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.*;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/15 13:25
 */
@SpringBootTest
public class InitialtionTest {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        delete();
        createPermission();
        createRole();
        authorize();
        createUser();
        createProduct();
        createOrder();
    }


    //权限（添加）
    @Test
    public void createPermission() {
        try {
            permissionService.create(new Permission("用户全部权限", "user:*:*"));
            permissionService.create(new Permission("用户列表", "user:find:*"));
            permissionService.create(new Permission("添加用户", "user:create:*"));
            permissionService.create(new Permission("删除用户", "user:delete:*"));
            permissionService.create(new Permission("更新用户", "user:update:*"));
            permissionService.create(new Permission("角色全部权限", "role:*:*"));
            permissionService.create(new Permission("角色列表", "role:find:*"));
            permissionService.create(new Permission("添加角色", "role:create:*"));
            permissionService.create(new Permission("删除角色", "role:delete:*"));
            permissionService.create(new Permission("更新角色", "role:update:*"));
            permissionService.create(new Permission("角色分配", "role:distribution:*"));
            permissionService.create(new Permission("权限列表", "permission:find:*"));
            permissionService.create(new Permission("权限分配", "permission:authorize:*"));
            permissionService.create(new Permission("产品全部权限", "product:*:*"));
            permissionService.create(new Permission("查找产品", "product:find:*"));
            permissionService.create(new Permission("添加产品", "product:create:*"));
            permissionService.create(new Permission("删除产品", "product:delete:*"));
            permissionService.create(new Permission("更新产品", "product:update:*"));
            permissionService.create(new Permission("订单全部权限", "order:*:*"));
            permissionService.create(new Permission("订单列表", "order:find:*"));
            permissionService.create(new Permission("添加订单", "order:create:*"));
            permissionService.create(new Permission("删除订单", "order:delete:*"));
            permissionService.create(new Permission("更新订单", "order:update:*"));
            permissionService.create(new Permission("日志列表", "log:find:*"));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    //角色（添加）
    @Test
    public void createRole() {
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

    //授权
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

    //用户（添加）
    @Test
    public void createUser() {
        try {
            userService.create(new User("admin", "admin", "超级管理员"));
            userService.create(new User("user", "user", "普通用户"));
            userService.create(new User("product", "product", "产品管理员"));
            userService.create(new User("order", "order", "订单管理员"));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    //产品（添加）
    @Test
    public void createProduct() {
        try {
            productService.create(new Product(null, null, "油炸五花肉", "油炸五花肉哈哈哈~", 1, new BigDecimal("29.8")));
            productService.create(new Product(null, null, "回锅肉", "回锅肉biubiubiu~", 0, new BigDecimal("9.9")));
            productService.create(new Product(null, null, "炭烤五花肉", "炭烤五花肉嘿嘿嘿~", 1, new BigDecimal("7.9")));
            productService.create(new Product(null, null, "红烧肉", "红烧肉哎呦喂~", 1, new BigDecimal("49.66")));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    //订单（添加）
    @Test
    public void createOrder() {
        ArrayList<OrderProduct> orderProducts = new ArrayList<>();
        try {
            orderProducts.add(new OrderProduct(null, productService.findProductByName("蛋黄派").getProductNum(), 3));
            orderProducts.add(new OrderProduct(null, productService.findProductByName("苹果").getProductNum(), 2));
            orderProducts.add(new OrderProduct(null, productService.findProductByName("棒棒糖").getProductNum(), 1));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
        boolean flag = false;
        try {
            flag = orderService.create(new Order(null, 0, new BaseArea(350000), new BaseArea(350200), new BaseArea(350203), "山竹公寓", "高毅忠", "13067130069", 1, 1, orderProducts, null));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
        System.out.println("flag = " + flag);
    }


    //删除
    @Test
    public void delete() {
        deletePermissions();
        deleteRole();
        deleteUser();
        deleteProduct();
        deleteOrder();
    }

    public void deletePermissions() {
        permissionService.deleteAll();
    }

    public void deleteRole() {
        roleService.deleteAll();
    }

    @Test
    public void deleteUser() {
        userService.deleteAll();
    }

    public void deleteProduct() {
        productService.deleteAll();
    }

    public void deleteOrder() {
        orderService.deleteAll();
    }

}
