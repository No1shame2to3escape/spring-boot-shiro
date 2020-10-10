package cn.realphago.springbootshiro.mapper;

import cn.realphago.springbootshiro.pojo.Permission;
import cn.realphago.springbootshiro.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/14 14:41
 */
@Mapper
public interface PermissionMapper extends AbstractMapper<Permission> {

    Integer authorize(Role role, Permission permission);

    Integer deAuthorizeByRoleNum(String roleNum);

    Integer deAuthorizeByPermissionNum(String permissionNum);

    List<Permission> findAll(String name);

}
