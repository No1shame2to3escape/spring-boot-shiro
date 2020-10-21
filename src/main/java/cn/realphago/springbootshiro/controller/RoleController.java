package cn.realphago.springbootshiro.controller;

import cn.realphago.springbootshiro.pojo.*;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.service.RoleService;
import cn.realphago.springbootshiro.service.UserService;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.JacksonUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 16:10
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;
    @Autowired
    private UserService userService;

    @RequiresPermissions(value = {"role:find:*", "permission:authorize:*"}, logical = Logical.OR)
    @RequestMapping("/elements/{currentPage}/{pageSize}")
    public String findAll(Model model, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        model.addAttribute("pageBean", new PageBeanUtils<Role>().findAll(currentPage, pageSize, service, null));
        return "role-list";
    }

    @RequiresPermissions(value = "role:create:*")
    @GetMapping("/create")
    public String create() {
        return "role-add";
    }

    @RequiresPermissions(value = "role:create:*")
    @PostMapping("/create")
    @ResponseBody
    public String create(Role role) throws JsonProcessingException, ParseException {

        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "添加角色失败");
        boolean flag = false;
        try {
            flag = service.create(role);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, e.getMessage()));
        }
        if (flag) {
            resultInfo = new ResultInfo<String>(200, "添加角色成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }

    @RequiresPermissions(value = "role:update:*")
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Model model) {

        Role role = null;
        try {
            role = service.findElementById(id);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return "400";
        }
        model.addAttribute("role", role);
        return "role-edit";
    }

    @RequiresPermissions(value = "role:update:*")
    @PostMapping("/update")
    @ResponseBody
    public String update(Role role) throws JsonProcessingException, ParseException {

        if (role == null)
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传递正确参数"));

        Role tempRole = null;
        try {
            tempRole = service.findRoleByRoleNum(role.getRoleNum());
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传递正确参数"));
        }
        if (DateFormatUtils.parse("2020-10-21 03:00:00", "yyyy-MM-dd HH:mm:ss").after(tempRole.getGmtCreate()))
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "基本数据不可修改"));


        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "修改角色失败");
        boolean flag = false;
        try {
            flag = service.update(role);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, e.getMessage()));
        }
        if (flag) {
            resultInfo = new ResultInfo<String>(200, "修改角色成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }

    @RequiresPermissions(value = "role:delete:*")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(String id) throws JsonProcessingException, ParseException {

        Role tempRole = null;
        try {
            tempRole = service.findElementById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传递正确参数"));
        }
        if (DateFormatUtils.parse("2020-10-21 03:00:00", "yyyy-MM-dd HH:mm:ss").after(tempRole.getGmtCreate()))
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "基本数据不可删除"));

        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "删除角色失败");
        boolean flag = false;
        try {
            flag = service.delete(id);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, e.getMessage()));
        }
        if (flag) {
            resultInfo = new ResultInfo<String>(200, "删除角色成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }

    @RequiresPermissions(value = "role:distribution:*")
    @GetMapping("/distribution/{username}")
    public String distributionRoles(@PathVariable("username") String username, Model model) {
        User user = null;
        try {
            user = userService.findUserByUsername(username);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return "error/4xx";
        }
        model.addAttribute("user", user);
        model.addAttribute("roles", service.findAll(null));
        return "user-authorize";
    }

    @RequiresPermissions(value = "role:distribution:*")
    @PostMapping("/distribution/{userNum}")
    @ResponseBody
    public String distributionRoles(@PathVariable("userNum") String userNum, String[] roleNums) throws JsonProcessingException, ParseException {

        User tempUser = null;
        try {
            tempUser = userService.findUserByUserNum(userNum);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传递正确参数"));
        }
        if (DateFormatUtils.parse("2020-10-21 03:00:00", "yyyy-MM-dd HH:mm:ss").after(tempUser.getGmtCreate()))
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "基本数据不可修改"));


        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "修改失败");
        boolean flag = false;
        try {
            flag = service.distributionRoles(userNum, roleNums);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传入正确参数"));
        }
        if (flag) {
            resultInfo = new ResultInfo<String>(200, "授权成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }


}
