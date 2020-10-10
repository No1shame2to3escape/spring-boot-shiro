package cn.realphago.springbootshiro.pojo;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/9 15:28
 */
public class ResultInf {

    private boolean flag;
    private String msg;

    public ResultInf() {
    }

    public ResultInf(boolean flag) {
        this.flag = flag;
    }

    public ResultInf(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResultInf{" +
                "flag=" + flag +
                ", msg='" + msg + '\'' +
                '}';
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
