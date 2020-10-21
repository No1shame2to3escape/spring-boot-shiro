package cn.realphago.springbootshiro.pojo;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/12 18:13
 */
public class Order implements Serializable {

    private String id; //主键
    private String orderNum; //产品编号
    private String comment;    //备注
    private Integer payType; //支付方式(0 支付宝 1 微信支付 2 其他)
    private BaseArea province; //省ID
    private BaseArea city; //城市ID
    private BaseArea county; //县ID
    private String address; //详细地址
    private String recipient; //收件人
    private String phone; //电话
    private Integer logistics; //物流
    private Integer status; //状态（0未付款，1未发货，2发货，3已签收）
    private List<OrderProduct> orderProductList;
    private BigDecimal totalPrice; //总额
    private String str_logistics;
    private String str_payType;
    private String str_status;
    private Date gmtCreate;    //添加时间
    private String str_gmtCreate;
    private Date gmtModified;  //最新修改时间
    private String str_gmtModified;

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", comment='" + comment + '\'' +
                ", payType=" + payType +
                ", province=" + province +
                ", city=" + city +
                ", county=" + county +
                ", address='" + address + '\'' +
                ", recipient='" + recipient + '\'' +
                ", phone='" + phone + '\'' +
                ", logistics='" + logistics + '\'' +
                ", status=" + status +
                ", orderProductList=" + orderProductList +
                ", totalPrice=" + totalPrice +
                ", str_payType='" + str_payType + '\'' +
                ", str_status='" + str_status + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", str_gmtCreate='" + str_gmtCreate + '\'' +
                ", gmtModified=" + gmtModified +
                ", str_gmtModified='" + str_gmtModified + '\'' +
                '}';
    }

    public Order(String comment, Integer payType, BaseArea province, BaseArea city, BaseArea
            county, String address, String recipient, String phone, Integer logistics, Integer status, List<OrderProduct> orderProductList, BigDecimal totalPrice) {
        this.comment = comment;
        this.payType = payType;
        this.province = province;
        this.city = city;
        this.county = county;
        this.address = address;
        this.recipient = recipient;
        this.phone = phone;
        this.logistics = logistics;
        this.status = status;
        this.orderProductList = orderProductList;
        this.totalPrice = totalPrice;
    }

    //字符串（status）
    public String getStr_status() {
        String result = "错误状态";
        if (status != null) {
            switch (status) {
                case 0:
                    return "未付款";
                case 1:
                    return "已付款";
                case 2:
                    return "未发货";
                case 3:
                    return "已发货";
                case 4:
                    return "已签收";
                default:
                    break;
            }
        }
        return result;
    }

    public String getStr_logistics() {
        String result = "未知物流";
        if (status != null) {
            switch (status) {
                case 0:
                    return "未存在物流";
                case 1:
                    return "顺丰快递";
                case 2:
                    return "京东快递";
                case 3:
                    return "中通快递";
                default:
                    break;
            }
        }
        return result;
    }

    //字符串（status）
    public String getStr_payType() {
        String result = "错误状态";
        if (status != null) {
            switch (status) {
                case 0:
                    return "支付宝";
                case 1:
                    return "微信";
                case 2:
                    return "货到付款";
                default:
                    break;
            }
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

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BaseArea getProvince() {
        return province;
    }

    public void setProvince(BaseArea province) {
        this.province = province;
    }

    public BaseArea getCity() {
        return city;
    }

    public void setCity(BaseArea city) {
        this.city = city;
    }

    public BaseArea getCounty() {
        return county;
    }

    public void setCounty(BaseArea county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getLogistics() {
        return logistics;
    }

    public void setLogistics(Integer logistics) {
        this.logistics = logistics;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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
