package cn.realphago.springbootshiro.mapper;

import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 17:52
 */
@Mapper
public interface RoleMapper extends AbstractMapper<Role> {

    List<Role> findRoleLIstByUserNum(@Param("userNum") String userNum);

    Integer distribution(@Param("userNum") String userNum, @PathVariable("roleNum") String roleNum);

    Integer reDistributionByUserNum(String userNum);

    Integer reDistributonByRoleNum(String roleNum);

    void reDistributionAll();

    Role findElementByRoleNum(String roleNum);
}
