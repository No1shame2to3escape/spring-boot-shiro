package cn.realphago.springbootshiro.service.impl;

import cn.realphago.springbootshiro.mapper.ProductMapper;
import cn.realphago.springbootshiro.mapper.ProductSpecificationMapper;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Product;
import cn.realphago.springbootshiro.pojo.ProductSpecification;
import cn.realphago.springbootshiro.service.ProductService;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Autowired
    private ProductSpecificationMapper productSpecificationMapper;


    @Override
    public Product findByProductName(String productName) {
        return mapper.findByProductName(productName);
    }

    //添加
    @Override
    public boolean create(Product product) {
        if (product.getId() == null) {
            product.setId(UUID.randomUUID().toString().replace("-", ""));
        }
        if (product.getProductNum() == null) {
            product.setProductNum(DateFormatUtils.getOrderNum("Product"));
        }
        Integer integer = mapper.create(product);
        return integer != null && integer == 1;
    }

    //添加(规格)
    @Override
    public boolean create(Product product, List<ProductSpecification> productSpecificationList) {
        String productNum = DateFormatUtils.getOrderNum("Product");
        product.setProductNum(productNum);
        boolean flag = create(product);
        if (!flag) {
            return flag;
        }
        if (productSpecificationList != null && productSpecificationList.size() != 0) {
            for (ProductSpecification productSpecification : productSpecificationList) {
                productSpecification.setProductNum(productNum);
                productSpecification.setSpecificationNum(DateFormatUtils.getOrderNum("spec"));
                productSpecification.setStatus(1);
                productSpecificationMapper.create(productSpecification);
            }
        }
        return false;
    }

    //分页查询
    @Override
    public PageBean<Product> findList(PageBean pageBean) {
        return new PageBeanUtils<Product>(mapper).findList(pageBean);
    }

    @Override
    public Product findProductById(String id) {
        return mapper.findElementById(id);
    }

    //删除
    @Override
    public boolean delete(Product product) {
        Integer integer = mapper.delete(product);
        productSpecificationMapper.deleteByProductNum(product.getProductNum());
        return integer != null && integer == 1;
    }

    @Override
    public boolean update(Product product) {
        Integer update = mapper.update(product);
        return update != null && update == 1;
    }


}
