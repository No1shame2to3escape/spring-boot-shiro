package cn.realphago.springbootshiro.service;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.SaltUtils;
import cn.realphago.springbootshiro.uitl.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.Date;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/16 2:31
 */
@SpringBootTest
public class UtilsTest {

    @Test
    public void stringUtils() {
        System.out.println(StringUtils.isEmpty(null));
        System.out.println(StringUtils.isEmpty("null"));
        System.out.println(StringUtils.isEmpty("    \n"));
    }

    @Test
    public void dateUtils() throws ParseException {
        System.out.println(DateFormatUtils.format(new Date(new Date().getTime() + 300000)));
    }

}
