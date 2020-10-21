package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.PageBean;

import java.util.Map;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 18:21
 */
public interface PageBeanService<T> {

    PageBean<T> findList(PageBean pageBean, Map<String, Object> addtionParamMap);

}
