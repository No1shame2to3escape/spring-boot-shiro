package cn.realphago.springbootshiro.controller;

import cn.realphago.springbootshiro.pojo.Permission;
import cn.realphago.springbootshiro.pojo.ResultInf;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.service.PermissionService;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 16:11
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService service;

    @RequestMapping("/elements/{currentPage}/{pageSize}")
    public String findAll(Model model, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        model.addAttribute("pageBean", new PageBeanUtils<Permission>().findAll(currentPage, pageSize, service, null));
        return "permission-list";
    }

    @PostMapping("/create")
    @ResponseBody
    public String create(Permission permission) throws JsonProcessingException {
        boolean flag = service.create(permission);
        ResultInf resultInf = null;
        resultInf = new ResultInf(false, "添加失败,权限已经存在");
        if (flag) {
            resultInf = new ResultInf(true, "添加成功");
        }
        return new ObjectMapper().writeValueAsString(resultInf);
    }

    @GetMapping("/create")
    public String create() {
        return "permission-add";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Model model) {
        model.addAttribute("permission", service.findPermissionById(id));
        return "permission-edit";
    }

    @PostMapping("/update")
    @ResponseBody
    public String update(Permission permission, Model model) throws JsonProcessingException {
        boolean update = service.update(permission);
        ResultInf resultInf = new ResultInf(true, "修改成功");
        if (!update) {
            resultInf = new ResultInf(false, "修改失败");
        }
        return new ObjectMapper().writeValueAsString(resultInf);
    }

    @PostMapping("/delete")
    @ResponseBody
    public String delete(Permission permission) {
        service.delete(permission);
        return "null";
    }

}
