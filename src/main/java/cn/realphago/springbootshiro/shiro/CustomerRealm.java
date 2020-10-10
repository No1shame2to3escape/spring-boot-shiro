package cn.realphago.springbootshiro.shiro;

import cn.realphago.springbootshiro.pojo.Permission;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.service.RoleService;
import cn.realphago.springbootshiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 9:38
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = userService.findUserByUsername((String) principalCollection.getPrimaryPrincipal());
        if (user != null) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//            List<Role> roles = roleService.findRoleLIstByUserNum(user.getUserNum());
            List<Role> roleList = user.getRoleList();
            for (Role role : roleList) {
                for (Permission permission : role.getPermissionList()) {
                    simpleAuthorizationInfo.addStringPermission(permission.getUrl());
                }
            }
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        User user = userService.findUserByUsername((String) authenticationToken.getPrincipal());
        if (user != null) {
            return new SimpleAuthenticationInfo(authenticationToken.getPrincipal(),
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()),
                    this.getName());
        }
        return null;
    }
}
