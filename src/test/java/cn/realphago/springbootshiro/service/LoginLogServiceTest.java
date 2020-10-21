package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.pojo.LoginLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/11 2:56
 */
@SpringBootTest
public class LoginLogServiceTest {

    @Autowired
    private LoginLogService loginLogService;

    @Test
    public void create() {
        System.out.println(loginLogService.create(new LoginLog(UUID.randomUUID().toString().replace("-", ""), new Date(), "admin")));
    }

}
