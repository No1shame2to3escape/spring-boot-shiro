package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.mapper.BaseAreaMapper;
import cn.realphago.springbootshiro.pojo.BaseArea;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/17 2:53
 */
@SpringBootTest
public class BaseAreaServiceTest {

    @Autowired
    private BaseAreaMapper baseAreaMapper;

    @Test
    public void findElementByCode() {
        BaseArea baseArea = baseAreaMapper.findElementByCode(350000);
        System.out.println("baseArea = " + baseArea);
    }

    @Test
    public void findElementByName() {
        BaseArea city = baseAreaMapper.findElementByName("厦门市");
        System.out.println("city = " + city);
    }

}
