package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 12:58
 */
public interface UserService extends PageBeanService<User> {

    //查询
    User findUserByUsername(String username) throws InvalidParameterException;

    //添加
    boolean create(User user) throws InvalidParameterException;

    //删除
    boolean delete(String id) throws InvalidParameterException;

    //删除（全部）
    boolean deleteAll();

    boolean update(User user) throws InvalidParameterException;

    //更新（id,status）
    boolean updateStatus(String id, Integer status) throws InvalidParameterException;

    User findUserById(String id) throws InvalidParameterException;

    User findUserByUserNum(String userNum) throws InvalidParameterException;

}
