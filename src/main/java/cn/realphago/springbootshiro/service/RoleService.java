package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 20:10
 */
public interface RoleService extends PageBeanService<Role> {

    List<Role> findRoleLIstByUserNum(String userNum);

    boolean create(Role role);

    boolean authorize(User user, Role role);

    //删除
    boolean delete(Role role);

    //删除权限(rid)
    boolean deAuthorizeByRoleNum(String roleNum);

    //修改
    boolean update(Role role);

    Role findElementById(String id);

    Role findRoleByName(String roleName);

    List<Role> findAll();
}
