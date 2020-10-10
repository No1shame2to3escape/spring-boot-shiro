package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.mapper.LogMapper;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.SysLog;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/17 15:53
 */
@SpringBootTest
public class LogServiceTest {

    @Autowired
    private LogService service;
    @Autowired
    private LogMapper logMapper;

    @Test
    public void findList() {
        PageBean<SysLog> all = new PageBeanUtils<SysLog>().findAll(1, 5, service, null);
        System.out.println("all = " + all);
    }


}
