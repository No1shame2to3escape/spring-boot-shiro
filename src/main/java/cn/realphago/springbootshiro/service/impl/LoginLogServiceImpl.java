package cn.realphago.springbootshiro.service.impl;

import cn.realphago.springbootshiro.mapper.LoginLogMapper;
import cn.realphago.springbootshiro.pojo.LoginLog;
import cn.realphago.springbootshiro.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/11 2:45
 */
@Service
@Transactional
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapper mapper;

    @Override
    public boolean create(LoginLog loginLog) {
        Integer integer = mapper.create(loginLog);
        return integer != null && integer == 1;
    }
}
