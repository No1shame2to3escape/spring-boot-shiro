package cn.realphago.springbootshiro.service.impl;

import cn.realphago.springbootshiro.mapper.PermissionMapper;
import cn.realphago.springbootshiro.mapper.RoleMapper;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Permission;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.service.PermissionService;
import cn.realphago.springbootshiro.uitl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
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
    @Autowired
    private RoleMapper roleMapper;

    //添加
    @Override
    public boolean create(Permission permission) throws InvalidParameterException {

        //参数空值检测
        if (permission == null)
            throw new InvalidParameterException("请传入正确参数");

        //数据处理，处理掉存入数据库中的String类的空格和换行
        permission.setName(StringUtils.handleSpacingAndShift(permission.getName()));
        permission.setUrl(StringUtils.handleSpacingAndShift(permission.getUrl()));

        //数据检测（不合格直接异常）
        if (StringUtils.isEmpty(permission.getName()) || StringUtils.isEmpty(permission.getUrl()))
            throw new InvalidParameterException("请传入正确参数");

        //为数据添加主键ID、角色编号
        permission.setId(UUID.randomUUID().toString().replace("-", ""));
        permission.setPermissionNum(DateFormatUtils.getOrderNum("Permi"));
        Integer integer = mapper.create(permission);
        if (integer == null || integer != 1)
            throw new InvalidParameterException("请传入正确参数");

        return true;
    }

    //删除
    @Override
    public boolean delete(String id) throws InvalidParameterException {

        //参数空值检测
        if (StringUtils.isEmpty(id))
            throw new InvalidParameterException("请传入正确参数");

        Permission permission = mapper.findElementById(id);
        if (permission == null)
            throw new InvalidParameterException("请传入正确参数");

        deAuthorizeByPermissionNum(permission.getPermissionNum());
        Integer integer = mapper.delete(id);
        if (integer == null || integer != 1)
            throw new InvalidParameterException("请传入正确参数");

        return true;
    }

    //删除（全部）
    @Override
    public boolean deleteAll() {
        mapper.deAuthorizeAll();
        mapper.deleteAll();
        return true;
    }

    //修改
    @Override
    public boolean update(Permission permission) throws InvalidParameterException {

        //参数空值检测
        if (permission == null)
            throw new InvalidParameterException("请传入正确参数");

        //数据处理，处理掉存入数据库中的String类的空格和换行
        permission.setName(StringUtils.handleSpacingAndShift(permission.getName()));
        permission.setUrl(StringUtils.handleSpacingAndShift(permission.getUrl()));
        permission.setId(StringUtils.handleSpacingAndShift(permission.getId()));

        //数据检测（不合格直接异常）
        if (StringUtils.isEmpty(permission.getName()) || StringUtils.isEmpty(permission.getUrl()) || StringUtils.isEmpty(permission.getId()))
            throw new InvalidParameterException("请传入正确参数");

        Integer integer = mapper.update(permission);
        if (integer == null || integer != 1)
            throw new InvalidParameterException("请传入正确参数");

        return true;
    }

    //查找(id)
    @Override
    public Permission findPermissionById(String id) throws InvalidParameterException {

        //参数空值检测
        if (StringUtils.isEmpty(id))
            throw new InvalidParameterException("请传入正确参数");

        return mapper.findElementById(id);
    }

    @Override
    public PageBean<Permission> findList(PageBean pageBean, Map<String, Object> addtionParamMap) {
        return new PageBeanUtils<Permission>(mapper).findList(pageBean, addtionParamMap);
    }

    @Override
    public boolean authorize(String roleNum, String permissionNum) throws InvalidParameterException {

        //参数空值检测
        if (StringUtils.isEmpty(roleNum) || StringUtils.isEmpty(permissionNum))
            throw new InvalidParameterException("请传入正确参数");

        Integer integer = mapper.authorize(roleNum, permissionNum);
        if (integer == null || integer != 1)
            throw new InvalidParameterException("请传入正确参数");

        return true;
    }

    @Override
    public Permission findPermissionByName(String name) throws InvalidParameterException {

        //参数空值检测
        if (StringUtils.isEmpty(name))
            throw new InvalidParameterException("请传入正确参数");

        return mapper.findElementByName(name);
    }

    @Override
    public boolean deAuthorizeByRoleNum(String roleNum) throws InvalidParameterException {

        //参数空值检测
        if (StringUtils.isEmpty(roleNum))
            throw new InvalidParameterException("请传入正确参数");

        Role role = roleMapper.findElementByRoleNum(roleNum);
        if (role == null)
            throw new InvalidParameterException("请传入正确参数");
        mapper.deAuthorizeByRoleNum(roleNum);

        return true;
    }

    @Override
    public boolean deAuthorizeByPermissionNum(String permissionNum) throws InvalidParameterException {

        //参数空值检测
        if (StringUtils.isEmpty(permissionNum))
            throw new InvalidParameterException("请传入正确参数");

        Permission permission = mapper.findElementByPermissionNum(permissionNum);
        if (permission == null)
            throw new InvalidParameterException("请传入正确参数");

        mapper.deAuthorizeByPermissionNum(permissionNum);

        return true;
    }

    @Override
    public List<Permission> findElementLikeUrl(String url) throws InvalidParameterException {

        if (StringUtils.isEmpty(url))
            throw new InvalidParameterException("请传入正确参数");

        return mapper.findElementLikeUrl(url);

    }

    @Override
    public boolean authorizePermissions(String roleNum, String[] permissionNums) throws InvalidParameterException {
        if (StringUtils.isEmpty(roleNum))
            throw new InvalidParameterException("参数错误");

        Role role = roleMapper.findElementByRoleNum(roleNum);

        if (role == null) {
            throw new InvalidParameterException("参数错误");
        }

        mapper.deAuthorizeByRoleNum(roleNum);

        if (permissionNums == null || permissionNums.length == 0)
            return true;

        for (String permissionNum : permissionNums) {
            if (permissionNum == null)
                throw new InvalidParameterException("参数错误");
            Integer integer = mapper.authorize(roleNum, permissionNum);
            if (integer != 1)
                throw new InvalidParameterException("参数错误");
        }

        return true;
    }
}
