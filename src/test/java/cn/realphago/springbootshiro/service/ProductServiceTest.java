package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.mapper.ProductMapper;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Product;
import cn.realphago.springbootshiro.pojo.ProductSpecification;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        Product product = new Product("蛋黄酥");
        List<ProductSpecification> productSpecificationList = new ArrayList<ProductSpecification>();
        productSpecificationList.add(new ProductSpecification(product.getProductNum(), "2个装"));
        productSpecificationList.add(new ProductSpecification(product.getProductNum(), "网红装"));
        productSpecificationList.add(new ProductSpecification(product.getProductNum(), "空杯装"));
        productService.create(product, productSpecificationList);
    }

    @Test
    public void delete() {
        productService.delete(productService.findByProductName("蛋黄酥"));
    }


    @Test
    public void findProductById() {
        Product product = productService.findProductById("14019d56886649ce964c00268f03082b");
        System.out.println("product = " + product);
    }

}
