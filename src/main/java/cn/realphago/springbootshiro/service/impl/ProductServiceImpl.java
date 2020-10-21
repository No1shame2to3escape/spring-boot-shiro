package cn.realphago.springbootshiro.service.impl;

import cn.realphago.springbootshiro.mapper.ProductMapper;
import cn.realphago.springbootshiro.mapper.UserMapper;
import cn.realphago.springbootshiro.pojo.ConditionCheck;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Product;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.pojo.exception.CheckException;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.service.ProductService;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import cn.realphago.springbootshiro.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 17:01
 */
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper mapper;

    //分页查询
    @Override
    public PageBean<Product> findList(PageBean pageBean, Map<String, Object> addtionParamMap) {
        return new PageBeanUtils<Product>(mapper).findList(pageBean, addtionParamMap);
    }

    //查询（id）
    @Override
    public Product findProductById(String id) throws InvalidParameterException {

        //参数为空判断
        if (StringUtils.isEmpty(id))
            throw new InvalidParameterException("请传入正确参数");

        return mapper.findElementById(id);
    }

    //查询（name）
    @Override
    public Product findProductByName(String name) throws InvalidParameterException {

        //参数为空判断
        if (StringUtils.isEmpty(name))
            throw new InvalidParameterException("请传入正确参数");

        return mapper.findElementByName(name);
    }

    //添加
    @Override
    public boolean create(Product product) throws InvalidParameterException {

        //参数为空判断
        if (StringUtils.isEmpty(product))
            throw new InvalidParameterException("请传入正确参数");

        //数据清理（删除字符串中的空格和换行）
        product.setName(StringUtils.handleSpacingAndShift(product.getName()));
        product.setProductDesc(StringUtils.handleSpacingAndShift(product.getProductDesc()));

        //数据检验不合格直接异常
        try {
            new ConditionCheck().checkXor(StringUtils.isEmpty(product.getName()))
                    .checkXor(StringUtils.isEmpty(product.getPrice()))
                    .checkXor(product.getPrice().compareTo(new BigDecimal(0.0)) == -1)
                    .end();
        } catch (CheckException e) {
            e.printStackTrace();
            throw new InvalidParameterException("请传入正确参数");
        }

        product.setId(UUID.randomUUID().toString().replace("-", ""));
        product.setProductNum(DateFormatUtils.getOrderNum("Product"));
        Integer integer = mapper.create(product);
        if (integer != 1)
            throw new InvalidParameterException("请传入正确参数");
        return true;
    }

    //删除（id）
    @Override
    public boolean delete(String id) throws InvalidParameterException {

        //参数为空判断
        if (StringUtils.isEmpty(id))
            throw new InvalidParameterException("请传入正确参数");

        Integer integer = mapper.delete(id);

        if (integer != 1)
            throw new InvalidParameterException("请传入正确参数");
        return true;
    }

    @Override
    public boolean deleteAll() {
        mapper.deleteAll();
        return true;
    }

    //更新
    @Override
    public boolean update(Product product) throws InvalidParameterException {

        //参数为空判断
        if (StringUtils.isEmpty(product))
            throw new InvalidParameterException("请传入正确参数");

        //数据清理（删除字符串中的空格和换行）
        product.setName(StringUtils.handleSpacingAndShift(product.getName()));
        product.setProductDesc(StringUtils.handleSpacingAndShift(product.getProductDesc()));

        //数据检验不合格直接异常
        try {
            new ConditionCheck().checkXor(StringUtils.isEmpty(product.getName()))
                    .checkXor(StringUtils.isEmpty(product.getPrice()))
                    .checkXor(product.getPrice().compareTo(new BigDecimal(0.0)) == -1)
                    .end();
        } catch (CheckException e) {
            e.printStackTrace();
            throw new InvalidParameterException("请传入正确参数");
        }

        Integer integer = mapper.update(product);

        if (integer != 1)
            throw new InvalidParameterException("请传入正确参数");

        return true;
    }

    //更新（status）
    @Override
    public boolean updateStatus(String id, Integer status) throws InvalidParameterException {

        //参数为空判断
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(status) || status < 0 || status > 1)
            throw new InvalidParameterException("请传入正确参数");

        Integer integer = mapper.updateStatus(id, status);

        if (integer != 1)
            throw new InvalidParameterException("请传入正确参数");

        return true;
    }
}
