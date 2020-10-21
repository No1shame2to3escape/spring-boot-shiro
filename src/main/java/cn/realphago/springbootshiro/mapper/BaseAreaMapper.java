package cn.realphago.springbootshiro.mapper;

import cn.realphago.springbootshiro.pojo.BaseArea;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/17 2:06
 */
@Mapper
public interface BaseAreaMapper extends AbstractMapper<BaseArea> {

    BaseArea findElementByCode(Integer code);

    BaseArea easyFindByCode(Integer code);

}
