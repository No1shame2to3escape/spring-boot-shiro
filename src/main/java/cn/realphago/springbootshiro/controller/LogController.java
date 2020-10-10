package cn.realphago.springbootshiro.controller;

import cn.realphago.springbootshiro.pojo.SysLog;
import cn.realphago.springbootshiro.service.LogService;
import cn.realphago.springbootshiro.uitl.CollectionUtils;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 16:12
 */
@Controller
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService service;

    @RequestMapping("/elements/{currentPage}/{pageSize}")
    public String findAll(Model model, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize, HttpServletRequest request) throws ParseException {
        //条件参数数据处理
        Map<String, String[]> preParameterMap = request.getParameterMap();
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        Set<String> keySet = preParameterMap.keySet();
        CollectionUtils.showMap(preParameterMap, 1);
        for (String key : keySet) {
            if (key.contains("Time")) {
                String time = preParameterMap.get(key)[0];
                if (!StringUtils.isEmpty(time) && !"null".equals(time)) {
                    parameterMap.put(key, DateFormatUtils.parse(preParameterMap.get(key)[0].replace("T", " "), "yyyy-MM-dd HH:mm"));
                } else {
                    parameterMap.put(key, null);
                }
                continue;
            }
            String value = preParameterMap.get(key)[0];
            if (!StringUtils.isEmpty(value) && !"null".equals(value)) {
                parameterMap.put(key, value);
            } else {
                parameterMap.put(key, null);
            }
        }
        model.addAttribute("pageBean", new PageBeanUtils<SysLog>().findAll(currentPage, pageSize, service, parameterMap));
        return "log-list";
    }

}
