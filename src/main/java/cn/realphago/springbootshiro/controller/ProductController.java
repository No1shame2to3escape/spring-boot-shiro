package cn.realphago.springbootshiro.controller;

import cn.realphago.springbootshiro.pojo.Product;
import cn.realphago.springbootshiro.pojo.ResultInf;
import cn.realphago.springbootshiro.pojo.User;
import cn.realphago.springbootshiro.service.ProductService;
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

    @RequiresPermissions("product:find:*")
    @RequestMapping("/elements/{currentPage}/{pageSize}")
    public String findAll(Model model, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        model.addAttribute("pageBean", new PageBeanUtils<Product>().findAll(currentPage, pageSize, service, null));
        return "product-list";
    }

    @RequiresPermissions("product:create:*")
    @PostMapping("/create")
    @ResponseBody
    public String create(Product product) throws JsonProcessingException {
        boolean bool = service.create(product);
        ResultInf resultInf = null;
        resultInf = new ResultInf(false, "添加失败,产品已经存在");
        if (bool) {
            resultInf = new ResultInf(true, "添加成功");
        }
        return new ObjectMapper().writeValueAsString(resultInf);
    }

    @RequiresPermissions("product:create:*")
    @GetMapping("/create")
    public String create(Model model) {
        return "product-add";
    }

    @RequiresPermissions("product:update:*")
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") String id) {
        Product product = service.findProductById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @RequiresPermissions("product:update:*")
    @PostMapping("/update")
    @ResponseBody
    public String update() {
        return "product-edit";
    }

    @RequiresPermissions("product:delete:*")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(String id) {
        Product product = new Product();
        product.setId(id);
        service.delete(product);
        return "redirect:/product/elements/1/5";
    }

}
