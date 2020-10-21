package cn.realphago.springbootshiro.mapper;

import cn.realphago.springbootshiro.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 15:20
 */
@Mapper
public interface ProductMapper extends AbstractMapper<Product> {

    Product findElementByProductNum(String productNum);

    Integer updateStatus(String id, Integer status);

}
