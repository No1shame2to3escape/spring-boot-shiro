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

    Integer create(T t);

    Integer create(Map map);

    Integer delete(T t);

    Integer delete(Map map);

    Integer update(T t);

    Integer update(Map map);

    List<T> findList(PageBean pageBean);

    Integer findTotalCount(@Param("parameterMap") Map<String, Object> parameterMap);

    T findElementById(String id);

    T findElementByName(String name);

}
