package cn.realphago.springbootshiro.mapper;

import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 15:26
 */
public interface AbstractMapper<T> {

    //添加
    Integer create(T t);

    Integer create(Map map);

    //删除
    Integer delete(T t);

    Integer delete(Map map);

    Integer delete(String id);

    Integer deleteAll();

    //更新
    Integer update(T t);

    Integer update(Map map);

    //分页查询
    List<T> findList(@Param("pageBean") PageBean pageBean, @Param("addtionParamMap") Map<String, Object> addtionParamMap);

    //查找记录总数
    Integer findTotalCount(@Param("addtionParamMap") Map<String, Object> addtionParamMap);

    //查找（id)
    T findElementById(String id);

    //查找（name)
    T findElementByName(String name);

}
