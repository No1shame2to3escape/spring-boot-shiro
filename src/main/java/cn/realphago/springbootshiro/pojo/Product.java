package cn.realphago.springbootshiro.pojo;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 12:39
 */
public class Product implements Serializable {

    private String id; //主键
    private String productNum; //产品编号
    private String name;    //产品名
    private String productDesc; //产品描述
    private Integer status; //产品状态（-1 删除 0下架，1上架）
    private BigDecimal price; //产品价格
    private String str_status;
    private Date gmtCreate;    //添加时间
    private String str_gmtCreate;
    private Date gmtModified;  //最新修改时间
    private String str_gmtModified;

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productNum='" + productNum + '\'' +
                ", name='" + name + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", status=" + status +
                ", price=" + price +
                ", str_status='" + str_status + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", str_gmtCreate='" + str_gmtCreate + '\'' +
                ", gmtModified=" + gmtModified +
                ", str_gmtModified='" + str_gmtModified + '\'' +
                '}';
    }

    public Product(String name, String productDesc, BigDecimal price) {
        this.name = name;
        this.productDesc = productDesc;
        this.price = price;
    }

    public Product(String id, String productNum, String name, String productDesc, Integer status, BigDecimal price) {
        this.id = id;
        this.productNum = productNum;
        this.name = name;
        this.productDesc = productDesc;
        this.status = status;
        this.price = price;
    }

    //字符串（status）
    public String getStr_status() {
        String result = "错误状态";
        if (status != null) {
            if (status == 0) result = "下架中";
            if (status == 1) result = "上架中";
        }
        return result;
    }

    //字符串（gmt_create）
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getGmtCreate() {
        return gmtCreate;
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
