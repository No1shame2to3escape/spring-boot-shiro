package cn.realphago.springbootshiro.service.impl;

import cn.realphago.springbootshiro.mapper.PermissionMapper;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Permission;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.service.PermissionService;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.MD5Utils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import cn.realphago.springbootshiro.uitl.SaltUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/14 15:08
 */
@Transactional
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper mapper;

    //添加
    @Override
    public boolean create(Permission permission) {
        permission.setId(UUID.randomUUID().toString().replace("-", ""));
        permission.setPermissionNum(DateFormatUtils.getOrderNum("Permi"));
        Integer integer = mapper.create(permission);
        return integer != null && integer == 1;
    }

    //删除
    @Override
    public boolean delete(Permission permission) {
        deAuthorizeByPermissionNum(permission.getId());
        Integer integer = mapper.delete(permission);
        return integer != null && integer > 0;
    }

    //修改
    @Override
    public boolean update(Permission permission) {
        boolean flag = false;
        Integer integer = mapper.update(permission);
        if (integer > 0) {
            flag = true;
        }
        return flag;
    }

    //查找(id)
    @Override
    public Permission findPermissionById(String id) {
        return mapper.findElementById(id);
    }

    @Override
    public PageBean<Permission> findList(PageBean pageBean) {
        return new PageBeanUtils<Permission>(mapper).findList(pageBean);
    }

    @Override
    public boolean authorize(Role role, Permission permission) {
        boolean flag = false;
        Integer integer = mapper.authorize(role, permission);
        if (integer > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public Permission findPermissionByName(String name) {
        return mapper.findElementByName(name);
    }

    @Override
    public boolean deAuthorizeByRoleNum(String roleNum) {
        boolean flag = false;
        Integer integer = mapper.deAuthorizeByRoleNum(roleNum);
        if (integer > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean deAuthorizeByPermissionNum(String permissionNum) {
        boolean flag = false;
        Integer integer = mapper.deAuthorizeByPermissionNum(permissionNum);
        if (integer > 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Permission> findAll(String name) {
        return mapper.findAll(name);
    }

}
