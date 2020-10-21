package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 20:10
 */
public interface RoleService extends PageBeanService<Role> {

    List<Role> findRoleLIstByUserNum(String userNum) throws InvalidParameterException;

    boolean create(Role role) throws InvalidParameterException;

    boolean distribution(User user, Role role) throws InvalidParameterException;

    //删除
    boolean delete(String id) throws InvalidParameterException;

    //删除权限(rid)
    boolean reDistributonByRoleNum(String roleNum) throws InvalidParameterException;

    //删除（全部）
    boolean deleteAll();

    //修改
    boolean update(Role role) throws InvalidParameterException;

    Role findElementById(String id) throws InvalidParameterException;

    Role findRoleByName(String roleName) throws InvalidParameterException;

    Role findRoleByRoleNum(String roleNum) throws InvalidParameterException;

    List<Role> findAll(Map<String, Object> addtionParamMap);

    boolean distributionRoles(String userNum, String[] roleNums) throws InvalidParameterException;
}
