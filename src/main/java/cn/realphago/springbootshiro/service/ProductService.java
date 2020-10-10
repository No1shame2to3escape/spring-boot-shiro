package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Product;
import cn.realphago.springbootshiro.pojo.ProductSpecification;
import cn.realphago.springbootshiro.pojo.User;

import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 16:59
 */
public interface ProductService extends PageBeanService<Product> {

    boolean create(Product product);

    boolean create(Product product, List<ProductSpecification> productSpecificationList);

    boolean delete(Product product);

    Product findByProductName(String productName);

    boolean update(Product product);

    Product findProductById(String id);

}
