package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.Order;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/13 10:27
 */
public interface OrderService extends PageBeanService<Order> {

    //添加
    boolean create(Order order) throws InvalidParameterException;

    //查询（id）
    Order findOrderById(String id) throws InvalidParameterException;

    //删除
    boolean delete(String id) throws InvalidParameterException;

    //删除（全部）
    boolean deleteAll();


}
