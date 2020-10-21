package cn.realphago.springbootshiro.service.impl;

import cn.realphago.springbootshiro.mapper.BaseAreaMapper;
import cn.realphago.springbootshiro.mapper.OrderMapper;
import cn.realphago.springbootshiro.mapper.OrderProductMapper;
import cn.realphago.springbootshiro.mapper.ProductMapper;
import cn.realphago.springbootshiro.pojo.*;
import cn.realphago.springbootshiro.pojo.exception.CheckException;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.service.OrderService;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import cn.realphago.springbootshiro.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/13 10:28
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper mapper;
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private BaseAreaMapper baseAreaMapper;

    //添加
    @Override
    public boolean create(Order order) throws InvalidParameterException {

        //参数为null检测
        if (order == null)
            throw new InvalidParameterException("请传入正确参数");


        BigDecimal totalPrice = new BigDecimal(0.0);

        //数据处理，处理掉存入数据库中的String类的空格和换行
        order.setComment(StringUtils.handleSpacingAndShift(order.getComment()));
        order.setAddress(StringUtils.handleSpacingAndShift(order.getAddress()));
        order.setRecipient(StringUtils.handleSpacingAndShift(order.getRecipient()));
        order.setPhone(StringUtils.handleSpacingAndShift(order.getPhone()));

        //检测数据，不合格直接返回false;
        try {
            new ConditionCheck().checkXor(order.getProvince() == null)
                    .checkXor(order.getCity() == null)
                    .checkXor(order.getCounty() == null)
                    .checkXor(order.getAddress() == null)
                    .checkXor(order.getRecipient() == null)
                    .checkXor(order.getPhone() == null)
                    .checkXor(order.getOrderProductList() == null)
                    .checkXor(order.getOrderProductList().size() == 0)
                    .checkXor(baseAreaMapper.easyFindByCode(order.getProvince().getCode()) == null)
                    .checkXor(baseAreaMapper.easyFindByCode(order.getCity().getCode()) == null)
                    .checkXor(baseAreaMapper.easyFindByCode(order.getCounty().getCode()) == null)
                    .checkXor(order.getStatus() == null)
                    .end();
        } catch (CheckException e) {
            e.printStackTrace();
            throw new InvalidParameterException("请传入正确参数");
        }

        //添加必要数据
        order.setId(StringUtils.getCommonUUID());
        order.setOrderNum(DateFormatUtils.getOrderNum("Order"));

        for (OrderProduct orderProduct : order.getOrderProductList()) {
            if (orderProduct.getCount() <= 0 || orderProduct.getCount() > 999)
                continue;
            Product product = productMapper.findElementByProductNum(orderProduct.getProductNum());
            if (product == null || product.getStatus() == 0)
                throw new InvalidParameterException("请传入正确参数");
            orderProduct.setOrderNum(order.getOrderNum());
            orderProduct.setName(product.getName());
            orderProduct.setPrice(product.getPrice());
            Integer integer = orderProductMapper.create(orderProduct);
            if (integer != 1) {
                throw new InvalidParameterException("请传入正确参数");
            }
            totalPrice = totalPrice.add(product.getPrice().multiply(new BigDecimal(orderProduct.getCount())));
        }

        order.setTotalPrice(totalPrice);
        Integer integer = mapper.create(order);
        if (integer != 1)
            throw new InvalidParameterException("请传入正确参数");

        return true;
    }

    //分页查询
    @Override
    public PageBean<Order> findList(PageBean pageBean, Map<String, Object> addtionParamMap) {
        return new PageBeanUtils<Order>(mapper).findList(pageBean, addtionParamMap);
    }

    //查询（id）
    @Override
    public Order findOrderById(String id) {
        return mapper.findElementById(id);
    }

    //删除
    @Override
    public boolean delete(String id) throws InvalidParameterException {
        if (StringUtils.isEmpty(id))
            throw new InvalidParameterException("请传入正确参数");

        Order order = mapper.findElementById(id);
        if (order == null) {
            throw new InvalidParameterException("请传入正确参数");
        }

        orderProductMapper.deleteByOrderNum(order.getOrderNum());
        Integer integer = mapper.delete(id);

        if (integer != 1)
            throw new InvalidParameterException("请传入正确参数");

        return true;
    }

    @Override
    public boolean deleteAll() {
        orderProductMapper.deleteAll();
        mapper.deleteAll();
        return true;
    }
}
