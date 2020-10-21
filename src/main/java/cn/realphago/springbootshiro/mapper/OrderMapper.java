package cn.realphago.springbootshiro.mapper;

import cn.realphago.springbootshiro.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/12 18:12
 */
@Mapper
public interface OrderMapper extends AbstractMapper<Order> {

}
