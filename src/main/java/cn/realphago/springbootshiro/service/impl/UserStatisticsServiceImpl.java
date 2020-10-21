package cn.realphago.springbootshiro.service.impl;

import cn.realphago.springbootshiro.mapper.LogMapper;
import cn.realphago.springbootshiro.mapper.LoginLogMapper;
import cn.realphago.springbootshiro.mapper.RoleMapper;
import cn.realphago.springbootshiro.mapper.UserMapper;
import cn.realphago.springbootshiro.pojo.UserStatistics;
import cn.realphago.springbootshiro.service.UserStatisticsService;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.GlobalInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/10 23:56
 */
@Transactional
@Service
public class UserStatisticsServiceImpl implements UserStatisticsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private LoginLogMapper loginLogMapper;

    //查找统计数
    @Override
    public UserStatistics statistics() {
        return new UserStatistics(userMapper.findTotalCount(null), roleMapper.findTotalCount(null), loginLogMapper.findTotalCount(), logMapper.findTotalCount(null), GlobalInfoUtils.getOnlineCount(), getWeekLoginCount(), getWeekDateString());
    }

    private Integer[] getWeekLoginCount() {
        Integer[] integers = new Integer[7];
        Date[] dates = eightDayZeroTime();
        for (int i = 0; i < integers.length; i++) {
            integers[i] = loginLogMapper.findCountInTime(dates[i], dates[i + 1]);
        }
        return integers;
    }

    private String[] getWeekDateString() {
        String[] strings = new String[7];
        Date[] dates = eightDayZeroTime();
        for (int i = 0; i < strings.length; i++) {
            strings[i] = DateFormatUtils.format(dates[i], "MM-dd");
        }
        return strings;
    }

    public static Date[] eightDayZeroTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) - 6, 0, 0, 0);
        Date[] dates = new Date[8];
        for (int i = 0; i < dates.length; i++) {
            dates[i] = cal.getTime();
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + 1, 0, 0, 0);
        }
        return dates;
    }

}
