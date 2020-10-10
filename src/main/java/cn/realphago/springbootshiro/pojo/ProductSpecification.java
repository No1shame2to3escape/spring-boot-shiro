package cn.realphago.springbootshiro.pojo;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;

import java.util.Date;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 12:40
 */
public class ProductSpecification {

    private String productNum;
    private String specificationNum;
    private Double price;
    private String specificaionDesc;
    private Integer status;
    private String str_status;
    private Date gmt_create;
    private String str_gmt_create;
    private Date gmt_modified;
    private String str_gmt_modified;

    public ProductSpecification() {
    }

    public ProductSpecification(String productNum, String specificaionDesc) {
        this(productNum,
                DateFormatUtils.getOrderNum("Spec"),
                99.9,
                specificaionDesc, 1,
                new Date());
    }

    public ProductSpecification(String productNum, String specificationNum, Double price, String specificaionDesc, Integer status, Date gmt_create) {
        this.productNum = productNum;
        this.specificationNum = specificationNum;
        this.price = price;
        this.specificaionDesc = specificaionDesc;
        this.status = status;
        this.gmt_create = gmt_create;
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

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getSpecificationNum() {
        return specificationNum;
    }

    public void setSpecificationNum(String specificationNum) {
        this.specificationNum = specificationNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSpecificaionDesc() {
        return specificaionDesc;
    }

    public void setSpecificaionDesc(String specificaionDesc) {
        this.specificaionDesc = specificaionDesc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
