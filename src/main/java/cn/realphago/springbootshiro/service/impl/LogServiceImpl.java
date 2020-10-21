package cn.realphago.springbootshiro.service.impl;

import cn.realphago.springbootshiro.mapper.LogMapper;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.SysLog;
import cn.realphago.springbootshiro.service.LogService;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/16 16:36
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper mapper;

    @Override
    public boolean create(SysLog sysLog) {
        boolean flag = false;
        Integer integer = mapper.create(sysLog);
        if (integer != null && integer > 0) {
            flag = true;
        }
        return false;
    }

    @Override
    public PageBean<SysLog> findList(PageBean pageBean, Map<String, Object> addtionParamMap) {
        return new PageBeanUtils<SysLog>(mapper).findList(pageBean, addtionParamMap);
    }

}
