package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.mapper.ProductMapper;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Product;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 17:05
 */
@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findList() {
        PageBean<Product> productPageBean = new PageBeanUtils<Product>().findAll(1, 5, productService, null);
        System.out.println("productPageBean = " + productPageBean);
    }

    @Test
    public void create() {
        try {
            productService.create(new Product("蛋黄派", "美味蛋糕", new BigDecimal("29.8")));
            productService.create(new Product("苹果", "冬季霸屏王", new BigDecimal("9.9")));
            productService.create(new Product("棒棒糖", "好甜啊", new BigDecimal("7.9")));
            productService.create(new Product("巧克力", "真的这么丝滑", new BigDecimal("49.66")));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            productService.delete(productService.findProductByName("蛋黄派").getId());
            productService.delete(productService.findProductByName("苹果").getId());
            productService.delete(productService.findProductByName("棒棒糖").getId());
            productService.delete(productService.findProductByName("巧克力").getId());
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void findProductById() {
        Product product = null;
        try {
            product = productService.findProductById(productService.findProductByName("蛋黄派").getId());
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
        System.out.println("product = " + product);
    }

    @Test
    public void findByProductName() {
        try {
            System.out.println(productService.findProductByName("蛋黄派"));
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        Product product = null;
        try {
            product = productService.findProductByName("蛋黄派");
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
        product.setPrice(new BigDecimal(9999.9));
        try {
            productService.update(product);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateStatus() {
        try {
            productService.updateStatus(productService.findProductByName("苹果").getId(), -1);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findElementById() {
        try {
            Product product = productService.findProductById(productService.findProductByName("苹果").getId());
            System.out.println("product = " + product);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
        }
    }

}
