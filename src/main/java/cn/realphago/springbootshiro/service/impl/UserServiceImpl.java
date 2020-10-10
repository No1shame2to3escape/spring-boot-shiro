package cn.realphago.springbootshiro.service.impl;

import cn.realphago.springbootshiro.mapper.RoleMapper;
import cn.realphago.springbootshiro.mapper.UserMapper;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.service.UserService;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.MD5Utils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import cn.realphago.springbootshiro.uitl.SaltUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 12:59
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;
    @Autowired
    private RoleMapper roleMapper;

    //查询（username）
    @Override
    public User findUserByUsername(String username) {
        return mapper.findUserByUsername(username);
    }

    //添加
    @Override
    public boolean create(User user) {
        user.setId(UUID.randomUUID().toString().replace("-", ""));
        user.setUserNum(DateFormatUtils.getOrderNum("User"));
        String salt = SaltUtils.getSalt(8);
        user.setSalt(salt);
        user.setPassword(MD5Utils.toHex(user.getPassword(), salt, 1024));
        Integer integer = mapper.create(user);
        return integer != null && integer == 1;
    }

    //添加（user,roles）
    @Override
    public boolean create(User user, String[] roles) {
        boolean flag = create(user);
        if (!flag) {
            return flag;
        }
        if (roles != null && roles.length != 0) {
            for (String role : roles) {
                Role role1 = new Role();
                role1.setRoleNum(role);
                roleMapper.authorize(user, role1);
            }
        }
        return true;
    }

    //分页查询
    @Override
    public PageBean<User> findList(PageBean pageBean) {
        return new PageBeanUtils<User>(mapper).findList(pageBean);
    }

    //删除
    @Override
    public boolean delete(User user) {
        roleMapper.deAuthorize(user);
        Integer integer = mapper.delete(user);
        return integer != null && integer == 1;
    }

    //更新
    @Override
    public boolean update(User user) {
        user.setGmt_modified(new Date());
        Integer update = mapper.update(user);
        return update != null && update > 0;
    }

    //更新（user,roles）
    @Override
    public boolean update(User user, String[] roles) {
        boolean flag = update(user);
        if (!flag) {
            return flag;
        }
        roleMapper.deAuthorize(user);
        if (roles != null && roles.length != 0) {
            for (String role : roles) {
                Role role1 = new Role();
                role1.setRoleNum(role);
                roleMapper.authorize(user, role1);
            }
        }
        return true;
    }

    //更新（id,status）
    @Override
    public boolean updateStatus(String id, Integer status) {
        Integer integer = mapper.updateStatus(id, status);
        return integer != null && integer > 0;
    }
}
