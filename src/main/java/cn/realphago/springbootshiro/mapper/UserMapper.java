package cn.realphago.springbootshiro.mapper;

import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/6 13:56
 */
@Mapper()
public interface UserMapper extends AbstractMapper<User> {

    //查找（username）
    User findUserByUsername(@Param("username") String username);

    //更新（id,status）
    Integer updateStatus(@Param("id") String id, @Param("status") Integer status);

}
