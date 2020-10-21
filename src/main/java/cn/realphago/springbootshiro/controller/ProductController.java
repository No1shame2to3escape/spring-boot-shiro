package cn.realphago.springbootshiro.controller;

import cn.realphago.springbootshiro.pojo.*;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.service.ProductService;
import cn.realphago.springbootshiro.uitl.JacksonUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 15:49
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    //分页查询
    @RequiresPermissions(value = "product:find:*")
    @RequestMapping("/elements/{currentPage}/{pageSize}")
    public String findAll(Model model, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        model.addAttribute("pageBean", new PageBeanUtils<Product>().findAll(currentPage, pageSize, service, null));
        return "product-list";
    }

    //添加
    @RequiresPermissions(value = "product:create:*")
    @PostMapping("/create")
    @ResponseBody
    public String create(Product product) throws JsonProcessingException {
        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "添加产品失败");
        boolean flag = false;
        try {
            flag = service.create(product);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传入正确值"));
        }
        if (flag) {
            resultInfo = new ResultInfo<String>(200, "添加产品成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }

    //添加（页面跳转）
    @RequiresPermissions(value = "product:create:*")
    @GetMapping("/create")
    public String create(Model model) {
        return "product-add";
    }

    //更新（页面跳转）
    @RequiresPermissions(value = "product:update:*")
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") String id) {
        Product product = null;
        try {
            product = service.findProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "400";
        }
        model.addAttribute("product", product);
        return "product-edit";
    }

    //更新
    @RequiresPermissions(value = "product:update:*")
    @PostMapping("/update")
    @ResponseBody
    public String update(Product product) throws JsonProcessingException {
        ResultInfo<String> resultInfo = new ResultInfo<String>(400, product.getName() + "修改失败");
        boolean flag = false;
        try {
            flag = service.update(product);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传入正确值"));
        }
        if (flag) {
            resultInfo = new ResultInfo<String>(200, product.getName() + "修改成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }

    //更新（status,id）
    @RequiresPermissions(value = "product:update:*")
    @PostMapping("/status/{id}/{status}")
    @ResponseBody
    public String updateStatus(@PathVariable("id") String id, @PathVariable("status") Integer status) throws JsonProcessingException {
        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "修改状态失败");
        boolean flag = false;
        try {
            flag = service.updateStatus(id, status);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传入正确值"));
        }
        if (flag) {
            resultInfo = new ResultInfo<String>(200, "修改状态成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }

    //删除
    @RequiresPermissions(value = "product:delete:*")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(String id) throws JsonProcessingException {
        ResultInfo<String> resultInfo = new ResultInfo<String>(400, "删除产品失败");
        boolean flag = false;
        try {
            flag = service.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<String>(400, "请传入正确值"));
        }
        if (flag)
            resultInfo = new ResultInfo<String>(200, "删除产品成功");
        return JacksonUtils.writeValueAsString(resultInfo);
    }


}
