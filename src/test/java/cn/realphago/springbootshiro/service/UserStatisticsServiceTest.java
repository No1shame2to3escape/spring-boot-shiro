package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.mapper.LoginLogMapper;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/11 1:27
 */
@SpringBootTest
public class UserStatisticsServiceTest {

    @Autowired
    private UserStatisticsService userStatisticsService;
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Test
    public void statistics() {
        System.out.println(userStatisticsService.statistics());
    }

    @Test
    public void time() {
        System.out.println(DateFormatUtils.format(new Date()));
    }
}
