package cn.realphago.springbootshiro.mapper;

import cn.realphago.springbootshiro.pojo.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/16 16:35
 */
@Mapper
public interface LogMapper extends AbstractMapper<SysLog> {

}
