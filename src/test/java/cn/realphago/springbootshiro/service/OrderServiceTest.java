package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.mapper.OrderMapper;
import cn.realphago.springbootshiro.mapper.OrderProductMapper;
import cn.realphago.springbootshiro.pojo.*;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/13 10:36
 */
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @Resource
    private OrderProductMapper orderProductMapper;
    @Resource
    private ProductService productService;

    @Test
    public void create() {
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

    @Test
    public void findOrderById() {
        Order order = null;
        try {
            order = orderService.findOrderById("0d7036e595284bf78d24182ad059c73c");
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
        System.out.println("order = " + order);
    }

    @Test
    public void delete() {
        boolean flag = false;
        try {
            flag = orderService.delete("5506701b72184c5d82765ccf57188eea");
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
        System.out.println("flag = " + flag);
    }

    @Test
    public void findList() {
        PageBean<Order> pageBean = orderService.findList(new PageBean(0, 5), null);
        for (Order element : pageBean.getElements()) {
            System.out.println("element = " + element);
        }
    }


}
