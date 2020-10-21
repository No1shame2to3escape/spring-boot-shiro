package cn.realphago.springbootshiro.mapper;

import cn.realphago.springbootshiro.pojo.Permission;
import cn.realphago.springbootshiro.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Scope;

import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/14 14:41
 */
@Mapper
public interface PermissionMapper extends AbstractMapper<Permission> {

    Integer authorize(String roleNum, String permissionNum);

    Integer deAuthorizeByRoleNum(String roleNum);

    Integer deAuthorizeByPermissionNum(String permissionNum);

    void deAuthorizeAll();

    Permission findElementByPermissionNum(String permissionNum);

    List<Permission> findElementLikeUrl(String url);

}
