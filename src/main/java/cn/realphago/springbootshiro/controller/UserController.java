package cn.realphago.springbootshiro.controller;

import cn.realphago.springbootshiro.pojo.ResultInf;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.service.RoleService;
import cn.realphago.springbootshiro.service.UserService;
import cn.realphago.springbootshiro.uitl.CollectionUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //登录
    @PostMapping("/login")
    public String login(Model model, String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
            return "redirect:/index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户不存在");
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
        }
        return "login";
    }

    //登出
    @PostMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }

    //分页查询
    @RequiresPermissions("user:find:*")
    @RequestMapping("/elements/{currentPage}/{pageSize}")
    public String findAll(Model model, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        model.addAttribute("pageBean", new PageBeanUtils<User>().findAll(currentPage, pageSize, service, null));
        return "user-list";
    }

    //添加（post）
    @RequiresPermissions("user:create:*")
    @PostMapping("/create")
    @ResponseBody
    public String create(User user, String[] roles) throws JsonProcessingException {
        boolean bool = service.create(user, roles);
        ResultInf resultInf = null;
        resultInf = new ResultInf(false, "添加失败,用户已经存在");
        if (bool) {
            resultInf = new ResultInf(true, "添加成功");
        }
        return new ObjectMapper().writeValueAsString(resultInf);
    }

    //添加（get）
    @RequiresPermissions("user:create:*")
    @GetMapping("/create")
    public String create(Model model) {
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList", roleList);
        return "user-add";
    }

    //删除
    @RequiresPermissions("user:delete:*")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(String id) {
        User user = new User();
        user.setId(id);
        service.delete(user);
        return "redirect:/user/elements/1/5";
    }

    //更新（post）
    @RequiresPermissions("user:update:*")
    @PostMapping("/update")
    @ResponseBody
    public String update(User user, String[] roles) throws JsonProcessingException {
        boolean update = service.update(user, roles);
        ResultInf resultInf = new ResultInf(false, "修改失败");
        if (update) {
            resultInf = new ResultInf(true, "修改成功");
        }
        return new ObjectMapper().writeValueAsString(resultInf);
    }

    //更新（get）
    @RequiresPermissions("user:update:*")
    @GetMapping("/update/{username}")
    public String update(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", service.findUserByUsername(username));
        model.addAttribute("roles", roleService.findAll());
        return "user-edit";
    }

    //更新（status,id）
    @PostMapping("/status/{id}/{status}")
    @ResponseBody
    public String updateStatus(@PathVariable("id") String id, @PathVariable("status") Integer status) throws JsonProcessingException {
        ResultInf resultInf = new ResultInf(false, "修改状态失败");
        boolean flag = service.updateStatus(id, status);
        if (flag) {
            resultInf = new ResultInf(true, "修改状态成功");
        }
        return new ObjectMapper().writeValueAsString(resultInf);
    }


}
