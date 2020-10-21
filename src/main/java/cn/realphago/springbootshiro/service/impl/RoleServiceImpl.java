package cn.realphago.springbootshiro.service.impl;

import cn.realphago.springbootshiro.mapper.PermissionMapper;
import cn.realphago.springbootshiro.mapper.RoleMapper;
import cn.realphago.springbootshiro.mapper.UserMapper;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.service.RoleService;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import cn.realphago.springbootshiro.uitl.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 20:12
 */
@Transactional
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper mapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    //查询(uid)
    @Override
    public List<Role> findRoleLIstByUserNum(String userNum) throws InvalidParameterException {

        //参数空值检测
        if (StringUtils.isEmpty(userNum))
            throw new InvalidParameterException("请传入正确参数");

        return mapper.findRoleLIstByUserNum(userNum);
    }

    //添加
    @Override
    public boolean create(Role role) throws InvalidParameterException {

        //参数空值检测
        if (role == null)
            throw new InvalidParameterException("请传入正确参数");

        //数据处理，处理掉存入数据库中的String类的空格和换行
        role.setName(StringUtils.handleSpacingAndShift(role.getName()));
        role.setRoleDesc(StringUtils.handleSpacingAndShift(role.getRoleDesc()));

        //数据检测（不合格直接异常）
        if (role.getName() == null)
            throw new InvalidParameterException("请传入正确参数");

        //为数据添加主键ID、角色编号
        role.setId(UUID.randomUUID().toString().replace("-", ""));
        role.setRoleNum(DateFormatUtils.getOrderNum("Role"));
        Integer integer = mapper.create(role);
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

        //数据处理，处理掉存入数据库中的String类的空格和换行
        id = StringUtils.handleSpacingAndShift(id);

        //数据检测（不合格直接异常）
        Role role = mapper.findElementById(id);
        if (role == null)
            throw new InvalidParameterException("请传入正确参数");

        reDistributonByRoleNum(role.getRoleNum());
        Integer integer = mapper.delete(id);
        if (integer == null || integer != 1)
            throw new InvalidParameterException("请传入正确参数");

        permissionMapper.deAuthorizeByRoleNum(role.getRoleNum());

        return true;
    }

    //删除（全部）
    @Override
    public boolean deleteAll() {
        mapper.deleteAll();
        return true;
    }

    //授权
    @Override
    public boolean distribution(User user, Role role) throws InvalidParameterException {

        //参数空值检测
        if (user == null || role == null)
            throw new InvalidParameterException("请传入正确参数");

        //数据处理，处理掉存入数据库中的String类的空格和换行
        user.setUserNum(StringUtils.handleSpacingAndShift(user.getUserNum()));
        role.setRoleNum(StringUtils.handleSpacingAndShift(role.getRoleNum()));

        //数据检测（不合格直接异常）
        if (userMapper.findElementByUserNum(user.getUserNum()) == null || mapper.findElementByRoleNum(role.getRoleNum()) == null)
            throw new InvalidParameterException("请传入正确参数");

        Integer integer = mapper.distribution(user.getUserNum(), role.getRoleNum());
        if (integer == null || integer != 1)
            throw new InvalidParameterException("请传入正确参数");

        return true;
    }

    //删除角色分配
    @Override
    public boolean reDistributonByRoleNum(String roleNum) throws InvalidParameterException {
        //参数空值检测
        if (StringUtils.isEmpty(roleNum))
            throw new InvalidParameterException("请传入正确参数");

        mapper.reDistributonByRoleNum(roleNum);

        return true;
    }

    //修改
    @Override
    public boolean update(Role role) throws InvalidParameterException {

        //参数空值检测
        if (role == null)
            throw new InvalidParameterException("请传入正确参数");

        //数据处理，处理掉存入数据库中的String类的空格和换行
        role.setName(StringUtils.handleSpacingAndShift(role.getName()));
        role.setRoleDesc(StringUtils.handleSpacingAndShift(role.getRoleDesc()));

        //数据检测（不合格直接异常）
        if (StringUtils.isEmpty(role.getRoleNum()) || StringUtils.isEmpty(role.getName()))
            throw new InvalidParameterException("请传入正确参数");

        Integer integer = mapper.update(role);

        if (integer == null || integer < 1)
            throw new InvalidParameterException("请传入正确参数");

        return true;
    }

    //分页查询
    @Override
    public PageBean<Role> findList(PageBean pageBean, Map<String, Object> addtionParamMap) {
        return new PageBeanUtils<Role>(mapper).findList(pageBean, addtionParamMap);
    }

    //查询(id)
    @Override
    public Role findElementById(String id) throws InvalidParameterException {

        //参数空值检测
        if (StringUtils.isEmpty(id))
            throw new InvalidParameterException("请传入正确参数");

        return mapper.findElementById(id);
    }

    //查询(roleName)
    @Override
    public Role findRoleByName(String name) throws InvalidParameterException {
        //参数空值检测
        if (StringUtils.isEmpty(name))
            throw new InvalidParameterException("请传入正确参数");
        return mapper.findElementByName(name);
    }

    //查询(全部)
    @Override
    public List<Role> findAll(Map<String, Object> addtionParamMap) {
        return mapper.findList(new PageBean(0, 5), addtionParamMap);
    }

    @Override
    public boolean distributionRoles(String userNum, String[] roleNums) throws InvalidParameterException {

        //参数为空检测
        if (StringUtils.isEmpty(userNum))
            throw new InvalidParameterException("请传入正确参数");

        User user = userMapper.findElementByUserNum(userNum);
        if (user == null)
            throw new InvalidParameterException("请传入正确参数");

        //数据检测，不正确则直接报异常
        for (int i = 0; i < roleNums.length; i++) {
            Role role = mapper.findElementByRoleNum(roleNums[i]);
            if (role == null)
                throw new InvalidParameterException("请传入正确参数");
        }

        mapper.reDistributionByUserNum(userNum);

        for (String roleNum : roleNums) {
            Integer integer = mapper.distribution(userNum, roleNum);
            if (integer != 1)
                throw new InvalidParameterException("请传入正确参数");
        }

        return true;
    }

    @Override
    public Role findRoleByRoleNum(String roleNum) throws InvalidParameterException {

        if (StringUtils.isEmpty(roleNum))
            throw new InvalidParameterException("请传入正确参数");

        return mapper.findElementByRoleNum(roleNum);
    }
}
