package cn.realphago.springbootshiro.service.impl;

import cn.realphago.springbootshiro.mapper.RoleMapper;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.service.RoleService;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
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

    //查询(uid)
    @Override
    public List<Role> findRoleLIstByUserNum(String userNum) {
        return mapper.findRoleLIstByUserNum(userNum);
    }

    //添加
    @Override
    public boolean create(Role role) {
        role.setId(UUID.randomUUID().toString().replace("-", ""));
        role.setRoleNum(DateFormatUtils.getOrderNum("role"));
        Integer integer = mapper.create(role);
        return integer != null && integer == 1;
    }

    //删除
    @Override
    public boolean delete(Role role) {
        Role elementById = mapper.findElementById(role.getId());
        if (elementById == null) {
            return false;
        }
        deAuthorizeByRoleNum(role.getRoleNum());
        Integer integer = mapper.delete(role);
        return integer != null && integer == 1;
    }

    //授权
    @Override
    public boolean authorize(User user, Role role) {
        Integer integer = mapper.authorize(user, role);
        return integer != null && integer == 1;
    }

    //删除权限
    @Override
    public boolean deAuthorizeByRoleNum(String roleNum) {
        return mapper.deAuthorizeByRoleNum(roleNum) == 1;
    }

    //修改
    @Override
    public boolean update(Role role) {
        role.setGmt_modified(new Date());
        Integer integer = mapper.update(role);
        return integer != null && integer == 1;
    }

    //分页查询
    @Override
    public PageBean<Role> findList(PageBean pageBean) {
        return new PageBeanUtils<Role>(mapper).findList(pageBean);
    }

    //查询(id)
    @Override
    public Role findElementById(String id) {
        return mapper.findElementById(id);
    }

    //查询(roleName)
    @Override
    public Role findRoleByName(String name) {
        return mapper.findElementByName(name);
    }

    //查询(全部)
    @Override
    public List<Role> findAll() {
        return mapper.findList(new PageBean(0, 5));
    }
}
