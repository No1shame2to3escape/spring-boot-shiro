package cn.realphago.springbootshiro.pojo;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 12:39
 */
public class Product {

    private String id;
    private String productNum;
    private String name;
    private String productDesc;
    private Integer status;
    private String str_status;
    private List<ProductSpecification> productSpecificationList;
    private Date gmt_create;
    private String str_gmt_create;
    private Date gmt_modified;
    private String str_gmt_modified;

    public Product() {
    }


    public Product(String name) {
        this(name,
                "好产品",
                1);
    }

    public Product(String name, String productDesc, Integer status) {
        this.id = UUID.randomUUID().toString().replace("-", "");
        this.productNum = DateFormatUtils.getOrderNum("Product");
        this.name = name;
        this.productDesc = productDesc;
        this.status = status;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getStr_status() {
        if (status != null) {
            return status == 0 ? "关闭" : "开启";
        }
        return "未知";
    }

    public String getStr_gmt_create() {
        if (gmt_create != null) {
            return DateFormatUtils.format(gmt_create);
        }
        return "未知";
    }

    public String getStr_gmt_modified() {
        if (gmt_modified != null) {
            return DateFormatUtils.format(gmt_modified);
        }
        return "未知";
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

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getGmt_modified() {
        return gmt_modified;
    }

    public void setGmt_modified(Date gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ProductSpecification> getProductSpecificationList() {
        return productSpecificationList;
    }

    public void setProductSpecificationList(List<ProductSpecification> productSpecificationList) {
        this.productSpecificationList = productSpecificationList;
    }
}
