package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.User;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 12:58
 */
public interface UserService extends PageBeanService<User> {

    User findUserByUsername(String username);

    boolean create(User user);

    boolean create(User user, String[] roles);

    boolean delete(User user);

    boolean update(User user);

    boolean update(User user, String[] roles);

    //更新（id,status）
    boolean updateStatus(String id, Integer status);

}
