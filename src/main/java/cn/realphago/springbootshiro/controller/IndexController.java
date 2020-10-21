package cn.realphago.springbootshiro.controller;

import cn.realphago.springbootshiro.pojo.ResultInfo;
import cn.realphago.springbootshiro.pojo.StatusInfo;
import cn.realphago.springbootshiro.pojo.UserStatistics;
import cn.realphago.springbootshiro.service.impl.UserStatisticsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/6 14:36
 */
@Controller
public class IndexController {

    @Autowired
    private UserStatisticsServiceImpl userStatisticsService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/dataBoard")
    public String dataBoard(Model model) {
        model.addAttribute("resultInfo", new ResultInfo<UserStatistics>(200, userStatisticsService.statistics()));
        return "dataBoard";
    }

}
