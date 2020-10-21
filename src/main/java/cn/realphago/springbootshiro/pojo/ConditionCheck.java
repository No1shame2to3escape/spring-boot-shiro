package cn.realphago.springbootshiro.pojo;

import cn.realphago.springbootshiro.pojo.exception.CheckException;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/17 21:55
 */
public class ConditionCheck {

    public ConditionCheck() {
    }

    public ConditionCheck checkXor(boolean flag) throws CheckException {
        if (flag)
            throw new CheckException("检测到true");
        return this;
    }

    public boolean end() {
        return false;
    }
}
