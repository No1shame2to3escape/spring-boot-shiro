package cn.realphago.springbootshiro.pojo;

import java.io.Serializable;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/10 23:07
 */
public class ResultInfo<T> implements Serializable {

    private Integer status;  //业务请求状态（200成功，400失败）
    private T data;  //业务结果数据

    public ResultInfo(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "status=" + status +
                ", data=" + data +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setMeta(Integer status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
