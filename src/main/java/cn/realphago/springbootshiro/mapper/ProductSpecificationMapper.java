package cn.realphago.springbootshiro.mapper;

import cn.realphago.springbootshiro.pojo.ProductSpecification;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 19:08
 */
@Mapper
public interface ProductSpecificationMapper extends AbstractMapper<ProductSpecification> {

    Integer deleteByProductNum(String pid);

}
