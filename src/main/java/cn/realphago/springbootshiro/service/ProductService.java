package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Product;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;

import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 16:59
 */
public interface ProductService extends PageBeanService<Product> {

    //添加
    boolean create(Product product) throws InvalidParameterException;

    //删除
    boolean delete(String id) throws InvalidParameterException;

    //删除（全部）
    boolean deleteAll();

    //查询（name）
    Product findProductByName(String name) throws InvalidParameterException;

    //更新
    boolean update(Product product) throws InvalidParameterException;

    //查询（id）
    Product findProductById(String id) throws InvalidParameterException;

    boolean updateStatus(String id, Integer status) throws InvalidParameterException;

}
