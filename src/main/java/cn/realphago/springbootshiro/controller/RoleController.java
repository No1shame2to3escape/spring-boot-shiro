package cn.realphago.springbootshiro.controller;

import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.ResultInf;
import cn.realphago.springbootshiro.pojo.Role;
import cn.realphago.springbootshiro.service.RoleService;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 16:10
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService service;

    @RequiresPermissions("role:find:*")
    @RequestMapping("/elements/{currentPage}/{pageSize}")
    public String findAll(Model model, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        model.addAttribute("pageBean", new PageBeanUtils<Role>().findAll(currentPage, pageSize, service, null));
        return "role-list";
    }

    @RequiresPermissions("role:create:*")
    @GetMapping("/create")
    public String create() {
        return "role-add";
    }

    @RequiresPermissions("role:create:*")
    @PostMapping("/create")
    @ResponseBody
    public String create(Role role) throws JsonProcessingException {
        boolean flag = service.create(role);
        ResultInf resultInf = new ResultInf(true, "添加成功");
        if (!flag) {
            resultInf = new ResultInf(flag, "添加失败");
        }
        return new ObjectMapper().writeValueAsString(resultInf);
    }

    @RequiresPermissions("role:update:*")
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Model model) {
        model.addAttribute("role", service.findElementById(id));
        return "role-edit";
    }

    @RequiresPermissions("role:update:*")
    @PostMapping("/update")
    @ResponseBody
    public String update(Role role, Model model) throws JsonProcessingException {
        boolean update = service.update(role);
        ResultInf resultInf = new ResultInf(true, "修改成功");
        if (!update) {
            resultInf = new ResultInf(false, "修改失败");
        }
        return new ObjectMapper().writeValueAsString(resultInf);
    }

    @RequiresPermissions("role:delete:*")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(Role role) {
        service.delete(role);
        return "null";
    }

}
