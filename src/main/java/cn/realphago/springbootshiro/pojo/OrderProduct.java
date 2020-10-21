package cn.realphago.springbootshiro.pojo;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/13 10:47
 */
public class OrderProduct implements Serializable {

    private String orderNum; //订单编号
    private String productNum; //产品编号
    private String name; //产品名
    private BigDecimal price; //价格
    private Integer count; //数量
    private Date gmtCreate;    //添加时间
    private String str_gmtCreate;
    private Date gmtModified;  //最新修改时间
    private String str_gmtModified;

    public OrderProduct() {
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "orderNum='" + orderNum + '\'' +
                ", productNum='" + productNum + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", gmtCreate=" + gmtCreate +
                ", str_gmtCreate='" + str_gmtCreate + '\'' +
                ", gmtModified=" + gmtModified +
                ", str_gmtModified='" + str_gmtModified + '\'' +
                '}';
    }

    public OrderProduct(String orderNum, String productNum, Integer count) {
        this.orderNum = orderNum;
        this.productNum = productNum;
        this.count = count;
    }

    public String getStr_gmtCreate() {
        String result = "错误时间";
        if (gmtCreate != null) {
            result = DateFormatUtils.format(gmtCreate);
        }
        return result;
    }

    //字符串（gmt_create）
    public String getStr_gmtModified() {
        String result = "未修改";
        if (gmtModified != null) {
            result = DateFormatUtils.format(gmtModified);
        }
        return result;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
