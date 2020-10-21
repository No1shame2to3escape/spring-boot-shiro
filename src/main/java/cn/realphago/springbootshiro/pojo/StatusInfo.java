package cn.realphago.springbootshiro.pojo;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/10 23:40
 */
public enum StatusInfo {

    SUCCESS(200, "操作成功"),
    ERROR(400, "操作失败");

    private final Integer status;
    private final String desc;

    StatusInfo(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "StatusInfo{" +
                "status=" + status +
                ", desc='" + desc + '\'' +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

}
