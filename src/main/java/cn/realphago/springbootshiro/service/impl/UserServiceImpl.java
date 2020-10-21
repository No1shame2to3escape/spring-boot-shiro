package cn.realphago.springbootshiro.service.impl;

import cn.realphago.springbootshiro.mapper.RoleMapper;
import cn.realphago.springbootshiro.mapper.UserMapper;
import cn.realphago.springbootshiro.pojo.ConditionCheck;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.pojo.exception.CheckException;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.service.UserService;
import cn.realphago.springbootshiro.uitl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

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
    public User findUserByUsername(String username) throws InvalidParameterException {

        if (StringUtils.isEmpty(username))
            throw new InvalidParameterException("请传入正确参数");

        return mapper.findUserByUsername(username);
    }


    //添加（user,roles）
    @Override
    public boolean create(User user) throws InvalidParameterException {

        //参数空值检测
        if (user == null)
            throw new InvalidParameterException("创建用户数据错误");

        //数据处理(处理掉存入数据库中的String类的空格和换行)
        user.setUsername(StringUtils.handleSpacingAndShift(user.getUsername()));
        user.setName(StringUtils.handleSpacingAndShift(user.getName()));

        //数据检测（不合格直接异常）
        try {
            new ConditionCheck().checkXor(StringUtils.isEmpty(user.getUsername()))
                    .checkXor(StringUtils.isEmpty(user.getPassword()))
                    .checkXor(StringUtils.isEmpty(user.getName()))
                    .checkXor(user.getGender() != null && user.getGender() != 0 && user.getGender() != 1)
                    .end();
        } catch (CheckException e) {
            throw new InvalidParameterException("创建用户数据错误");
        }

        //为数据添加主键ID、用户编号、密码加密并生成随机盐
        user.setId(StringUtils.getCommonUUID());
        user.setUserNum(DateFormatUtils.getOrderNum("User"));
        String salt = SaltUtils.getSalt(8);
        user.setSalt(salt);
        user.setPassword(MD5Utils.toHex(user.getPassword(), salt, 1024));

        //提交数据并判断结果
        Integer integer = mapper.create(user);
        if (integer != 1)
            throw new InvalidParameterException("创建用户数据错误");


        return true;
    }

    //分页查询
    @Override
    public PageBean<User> findList(PageBean pageBean, Map<String, Object> addtionParamMap) {
        return new PageBeanUtils<User>(mapper).findList(pageBean, addtionParamMap);
    }

    //删除
    @Override
    public boolean delete(String id) throws InvalidParameterException {

        //参数空值检测
        if (StringUtils.isEmpty(id))
            throw new InvalidParameterException("删除参数错误");

        //数据处理(处理掉存入数据库中的String类的空格和换行)
        id = StringUtils.handleSpacingAndShift(id);

        //数据检测（不合格直接异常）
        if (StringUtils.isEmpty(id))
            throw new InvalidParameterException("删除参数错误");

        //获取结果并返回数据
        roleMapper.reDistributionByUserNum(mapper.findElementById(id).getUserNum());
        Integer integer = mapper.delete(id);

        return integer != null && integer == 1;
    }

    @Override
    public boolean deleteAll() {
        roleMapper.reDistributionAll();
        mapper.deleteAll();
        return true;
    }

    //更新（user,roles）
    @Override
    public boolean update(User user) throws InvalidParameterException {

        //参数空值检测
        if (user == null)
            throw new InvalidParameterException("修改用户数据错误");

        //数据处理，处理掉存入数据库中的String类的空格和换行
        user.setName(StringUtils.handleSpacingAndShift(user.getName()));

        //数据检测（不合格直接异常）
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getId()) || user.getGender() == null || (user.getGender() != 1 && user.getGender() != 0))
            throw new InvalidParameterException("修改用户数据参数错误");

        Integer integer = mapper.update(user);
        if (integer == null || integer != 1)
            throw new InvalidParameterException("修改用户数据参数错误");

        return true;
    }

    //更新（id,status）
    @Override
    public boolean updateStatus(String id, Integer status) throws InvalidParameterException {

        if (id == null || status == null || (status != 1 && status != 0))
            throw new InvalidParameterException("修改数据参数错误");

        Integer integer = mapper.updateStatus(id, status);
        return integer != null && integer == 1;
    }


    @Override
    public User findUserById(String id) throws InvalidParameterException {
        if (StringUtils.isEmpty(id))
            throw new InvalidParameterException("请传入正确参数");
        return mapper.findElementById(id);
    }

    @Override
    public User findUserByUserNum(String userNum) throws InvalidParameterException {
        if (StringUtils.isEmpty(userNum))
            throw new InvalidParameterException("请传入正确参数");
        return mapper.findElementByUserNum(userNum);
    }
}
