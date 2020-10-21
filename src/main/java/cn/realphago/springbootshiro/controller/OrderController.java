package cn.realphago.springbootshiro.controller;

import cn.realphago.springbootshiro.mapper.BaseAreaMapper;
import cn.realphago.springbootshiro.pojo.*;
import cn.realphago.springbootshiro.pojo.exception.InvalidParameterException;
import cn.realphago.springbootshiro.service.OrderService;
import cn.realphago.springbootshiro.service.ProductService;
import cn.realphago.springbootshiro.uitl.JacksonUtils;
import cn.realphago.springbootshiro.uitl.PageBeanUtils;
import cn.realphago.springbootshiro.uitl.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/7 15:37
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;
    @Autowired
    private ProductService productService;
    @Autowired
    private BaseAreaMapper baseAreaMapper;

    //分页查询
    @RequiresPermissions(value = "order:find:*")
    @RequestMapping("/elements/{currentPage}/{pageSize}")
    public String findAll(Model model, @PathVariable("currentPage") Integer currentPage, @PathVariable("pageSize") Integer pageSize) {
        model.addAttribute("pageBean", new PageBeanUtils<Order>().findAll(currentPage, pageSize, service, null));
        return "order-list";
    }

    @RequiresPermissions(value = "order:create:*")
    @GetMapping("/create")
    public String addPage(Model model) {
        Map<String, Object> addtionalParam = new HashMap<String, Object>();
        addtionalParam.put("status", 1);
        model.addAttribute("productList", productService.findList(new PageBean(0, 5), addtionalParam));
        model.addAttribute("baseArea", baseAreaMapper.findElementByCode(350000));
        return "order-add";
    }

    @RequiresPermissions(value = "order:create:*")
    @PostMapping("/create")
    @ResponseBody
    public String create(HttpServletRequest request) throws JsonProcessingException {
        Order order = new Order();
        String recipient = request.getParameter("recipient");
        if (recipient != null) {
            order.setRecipient(recipient);
        }
        String phone = request.getParameter("phone");
        if (phone != null) {
            order.setPhone(phone);
        }
        String provinceId = request.getParameter("provinceId");
        if (provinceId != null) {
            order.setProvince(new BaseArea(Integer.parseInt(provinceId)));
        }
        String cityId = request.getParameter("cityId");
        if (cityId != null) {
            order.setCity(new BaseArea(Integer.parseInt(cityId)));
        }
        String countyId = request.getParameter("countyId");
        if (countyId != null) {
            order.setCounty(new BaseArea(Integer.parseInt(countyId)));
        }
        String address = request.getParameter("address");
        if (countyId != null) {
            order.setAddress(address);
        }
        String logistics = request.getParameter("logistics");
        if (logistics != null) {
            order.setLogistics(Integer.parseInt(logistics));
        }
        String payType = request.getParameter("payType");
        if (payType != null) {
            order.setPayType(Integer.parseInt(payType));
        }
        String status = request.getParameter("status");
        if (payType != null) {
            order.setStatus(Integer.parseInt(status));
        }
        order.setComment(request.getParameter("comment"));
        List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
        for (int i = 0; i < request.getParameterMap().size(); i++) {
            String productNum = request.getParameter("productNum" + i);
            String count = request.getParameter("count" + i);
            if (productNum == null || count == null)
                break;
            orderProductList.add(new OrderProduct(null, productNum, Integer.parseInt(count)));
        }
        order.setOrderProductList(orderProductList);
        boolean flag = false;
        try {
            flag = service.create(order);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<>(400, "请传入正确值"));
        }
        ResultInfo<String> resultInfo = new ResultInfo<>(400, "添加订单失败");
        if (flag) {
            resultInfo = new ResultInfo<String>(200, "添加订单成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }

    @RequiresPermissions(value = "order:delete:*")
    @PostMapping("/delete")
    @ResponseBody
    public String delete(String id) throws JsonProcessingException {
        ResultInfo<String> resultInfo = new ResultInfo<>(400, "删除订单失败");
        boolean flag = false;
        try {
            flag = service.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return JacksonUtils.writeValueAsString(new ResultInfo<>(400, "请传入正确值"));
        }
        if (flag) {
            resultInfo = new ResultInfo<String>(200, "删除订单成功");
        }
        return JacksonUtils.writeValueAsString(resultInfo);
    }

    @RequiresPermissions(value = "order:find:*")
    @GetMapping("/completeOrderInfo/{id}")
    public String completeOrderInfo(@PathVariable("id") String id, Model model) {
        if (StringUtils.isEmpty(id))
            return "error/4xx";
        Order order = null;
        try {
            order = service.findOrderById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "400";
        }
        model.addAttribute("order", order);
        return "order-info";
    }

}
