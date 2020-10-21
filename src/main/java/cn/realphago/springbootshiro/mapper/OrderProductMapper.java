package cn.realphago.springbootshiro.mapper;

import cn.realphago.springbootshiro.pojo.OrderProduct;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/13 15:29
 */
@Mapper
public interface OrderProductMapper extends AbstractMapper<OrderProduct> {

    Integer deleteByOrderNum(String orderNum);

}
