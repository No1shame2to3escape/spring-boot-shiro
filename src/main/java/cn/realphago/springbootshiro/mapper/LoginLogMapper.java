package cn.realphago.springbootshiro.mapper;

import cn.realphago.springbootshiro.pojo.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/11 0:08
 */
@Mapper
public interface LoginLogMapper {

    //查找总记录数
    Integer findTotalCount();

    //查找范围时间内记录数
    Integer findCountInTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

    //添加
    Integer create(LoginLog loginLog);

}
