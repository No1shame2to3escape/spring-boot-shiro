package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.SysLog;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/16 16:35
 */
public interface LogService extends PageBeanService<SysLog> {

    boolean create(SysLog sysLog);

}
