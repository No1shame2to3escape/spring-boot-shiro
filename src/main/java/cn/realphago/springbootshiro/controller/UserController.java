package cn.realphago.springbootshiro.controller;

import cn.realphago.springbootshiro.pojo.*;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.service.LoginLogService;
import cn.realphago.springbootshiro.service.RoleService;
import cn.realphago.springbootshiro.service.UserService;
import cn.realphago.springbootshiro.uitl.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/6 14:06
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private RoleService roleService;
    @Autowired
    private LoginLogService loginLogService;

    //登录
    @PostMapping("/login")
    public String login(Model model, String username, String password, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            loginLogService.create(new LoginLog(DateFormatUtils.getOrderNum("login"), new Date(), username));
            GlobalInfoUtils.updateOperationMap(request.getSession(), new Date());
            return "redirect:/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户不存在");
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
        }
        return "login";
    }

    //登出
    @RequiresAuthentication
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        GlobalInfoUtils.removeOperationMap(request.getSession());
        return "redirect:/";
    }

    //分页查询
    @RequiresPermissions(value = {"user:find:*", "role:distribution:*"}, logical = Logical.OR)
    @RequestMapping("/elements/{currentPage}/{pageSize}")
    public String findAll(Model model, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        //使用PageBeanUtils 代理进行 数据的获取
        model.addAttribute("pageBean", new PageBeanUtils<User>().findAll(currentPage, pageSize, service, null));
        return "user-list";
    }

    //添加（post）
    @RequiresPermissions(value = "user:create:*")
    @PostMapping("/create")
    @ResponseBody
    public String create(User user) throws JsonProcessingException {
        ResultInfo<String> resultInfo = new ResultInfo<>(400, "添加失败");
        boolean flag = false;

        try {
            flag = service.create(user);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传递正确参数"));
        }

        if (flag)
            resultInfo = new ResultInfo<>(200, "添加成功");

        return JacksonUtils.writeValueAsString(resultInfo);
    }

    //添加（get）
    @RequiresPermissions(value = "user:create:*")
    @GetMapping("/create")
    public String create(Model model) {
        return "user-add";
    }

    //删除
    @RequiresPermissions(value = "user:delete:*")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") String id) throws JsonProcessingException, ParseException {
        User user = null;
        try {
            user = service.findUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传递正确参数"));
        }
        if (DateFormatUtils.parse("2020-10-21 03:00:00", "yyyy-MM-dd HH:mm:ss").after(user.getGmtCreate()))
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "基本数据不可删除"));

        ResultInfo<String> resultInfo = new ResultInfo<>(400, "删除失败");
        boolean flag = false;

        try {
            flag = service.delete(id);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传递正确参数"));
        }

        if (flag)
            resultInfo = new ResultInfo<>(200, "删除成功");

        return JacksonUtils.writeValueAsString(resultInfo);
    }

    //更新（post）
    @RequiresPermissions(value = "user:update:*")
    @PostMapping("/update")
    @ResponseBody
    public String update(User user) throws JsonProcessingException, ParseException {

        User tempUser = null;
        try {
            tempUser = service.findUserById(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传递正确参数"));
        }
        if (DateFormatUtils.parse("2020-10-21 03:00:00", "yyyy-MM-dd HH:mm:ss").after(tempUser.getGmtCreate()))
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "基本数据不可修改"));


        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "修改失败");
        boolean flag = false;
        try {
            flag = service.update(user);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传入正确参数"));
        }
        if (flag)
            resultInfo = new ResultInfo<String>(200, "修改成功");

        return JacksonUtils.writeValueAsString(resultInfo);
    }

    //更新（get）
    @RequiresPermissions(value = "user:update:*")
    @GetMapping("/update/{username}")
    public String update(@PathVariable("username") String username, Model model) {
        User user = null;
        try {
            user = service.findUserByUsername(username);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return "error/4xx";
        }
        model.addAttribute("user", user);
        return "user-edit";
    }

    //更新（status,id）
    @RequiresPermissions(value = "user:update:*")
    @PostMapping("/update/{id}/{status}")
    @ResponseBody
    public String updateStatus(@PathVariable("id") String id, @PathVariable("status") Integer status) throws JsonProcessingException, ParseException {

        User tempUser = null;
        try {
            tempUser = service.findUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传递正确参数"));
        }
        if (DateFormatUtils.parse("2020-10-21 03:00:00", "yyyy-MM-dd HH:mm:ss").after(tempUser.getGmtCreate()))
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "基本数据不可修改"));


        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "修改失败");
        boolean flag = false;
        try {
            flag = service.updateStatus(id, status);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            resultInfo = new ResultInfo<String>(400, "修改失败，请传入正确的参数");
        }
        if (flag)
            resultInfo = new ResultInfo<String>(200, "修改成功");

        return JacksonUtils.writeValueAsString(resultInfo);
    }


}
