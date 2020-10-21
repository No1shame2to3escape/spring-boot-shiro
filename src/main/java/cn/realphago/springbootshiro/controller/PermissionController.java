package cn.realphago.springbootshiro.controller;

import cn.realphago.springbootshiro.pojo.*;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.service.PermissionService;
import cn.realphago.springbootshiro.service.RoleService;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.JacksonUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 16:11
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService service;
    @Autowired
    private RoleService roleService;

    @RequiresPermissions(value = "permission:find:*")
    @RequestMapping("/elements/{currentPage}/{pageSize}")
    public String findAll(Model model, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        model.addAttribute("pageBean", new PageBeanUtils<Permission>().findAll(currentPage, pageSize, service, null));
        return "permission-list";
    }

    @RequiresPermissions(value = "permission:create:*")
    @PostMapping("/create")
    @ResponseBody
    public String create(Permission permission) throws JsonProcessingException {
        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "添加失败");
        boolean flag = false;
        try {
            flag = service.create(permission);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, e.getMessage()));
        }
        if (flag) {
            resultInfo = new ResultInfo<String>(200, "添加成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }

    @RequiresPermissions(value = "permission:create:*")
    @GetMapping("/create")
    public String create() {
        return "permission-add";
    }

    @RequiresPermissions(value = "permission:update:*")
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Model model) {
        Permission permission = null;
        try {
            permission = service.findPermissionById(id);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return "400";
        }
        model.addAttribute("permission", permission);
        return "permission-edit";
    }

    @RequiresPermissions(value = "permission:update:*")
    @PostMapping("/update")
    @ResponseBody
    public String update(Permission permission, Model model) throws JsonProcessingException {
        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "添加失败");
        boolean flag = false;
        try {
            flag = service.update(permission);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, e.getMessage()));
        }
        if (flag) {
            resultInfo = new ResultInfo<String>(200, "添加成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }

    @RequiresPermissions(value = "permission:delete:*")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(String permissionId) throws JsonProcessingException {
        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "删除失败");
        boolean flag = false;
        try {
            flag = service.delete(permissionId);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, e.getMessage()));
        }
        if (flag) {
            resultInfo = new ResultInfo<String>(200, "删除成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }

    @RequiresPermissions(value = "permission:authorize:*")
    @GetMapping("/authrize/{id}")
    public String authorizePermissions(@PathVariable("id") String id, Model model) {
        Role role = null;
        Map<String, List<Permission>> permissionMap = new HashMap<String, List<Permission>>();
        try {
            role = roleService.findElementById(id);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return "error/5xx";
        }
        if (role == null)
            return "error/5xx";
        model.addAttribute("role", role);
        PageBean<Permission> permissionPageBean = null;
        try {
            permissionPageBean = new PageBeanUtils<Permission>().findAll(0, Integer.SIZE, service, null);
        } catch (Exception e) {
            e.printStackTrace();
            return "error/5xx";
        }
        if (permissionPageBean == null)
            model.addAttribute("permissionMap", null);
        List<Permission> permissionList = null;
        try {
            permissionList = service.findElementLikeUrl("order");
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return "error/5xx";
        }
        if (permissionList != null && permissionList.size() > 0)
            permissionMap.put("order", permissionList);
        permissionList = null;
        try {
            permissionList = service.findElementLikeUrl("product");
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return "error/5xx";
        }
        if (permissionList != null && permissionList.size() > 0)
            permissionMap.put("product", permissionList);
        permissionList = null;
        try {
            permissionList = service.findElementLikeUrl("user");
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return "error/5xx";
        }
        if (permissionList != null && permissionList.size() > 0)
            permissionMap.put("user", permissionList);
        permissionList = null;
        try {
            permissionList = service.findElementLikeUrl("role");
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return "error/5xx";
        }
        if (permissionList != null && permissionList.size() > 0)
            permissionMap.put("role", permissionList);
        permissionList = null;
        try {
            permissionList = service.findElementLikeUrl("permission");
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return "error/5xx";
        }
        if (permissionList != null && permissionList.size() > 0)
            permissionMap.put("permission", permissionList);
        permissionList = null;
        try {
            permissionList = service.findElementLikeUrl("log");
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return "error/5xx";
        }
        if (permissionList != null && permissionList.size() > 0)
            permissionMap.put("log", permissionList);
        permissionList = null;
        model.addAttribute("permissionMap", permissionMap);
        return "role-authorize";
    }

    @RequiresPermissions(value = "permission:authorize:*")
    @PostMapping("/authrize/{id}")
    @ResponseBody
    public String authorizePermissions(@PathVariable("id") String id, String[] permissionNums) throws JsonProcessingException, ParseException {

        Role tempRole = null;
        try {
            tempRole = roleService.findElementById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传递正确参数"));
        }
        if (DateFormatUtils.parse("2020-10-21 03:00:00", "yyyy-MM-dd HH:mm:ss").after(tempRole.getGmtCreate()))
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "基本数据不可修改"));

        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "授权失败");
        Role role = null;
        try {
            role = roleService.findElementById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传入正确参数"));
        }
        if (role == null)
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传入正确参数"));

        boolean flag = false;

        try {
            flag = service.authorizePermissions(role.getRoleNum(), permissionNums);
        } catch (InvalidParameterException e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传入正确参数"));
        }

        if (flag) {
            resultInfo = new ResultInfo<String>(200, "授权成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }

}
