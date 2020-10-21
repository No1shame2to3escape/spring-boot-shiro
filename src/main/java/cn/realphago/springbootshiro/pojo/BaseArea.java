package cn.realphago.springbootshiro.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/17 2:28
 */
public class BaseArea implements Serializable {

    private Integer id; //主键ID
    private Integer prevId; //上一级的ID
    private Integer code; //区域唯一代码
    private Integer level;  //所属层级
    private String name; //区域名
    private List<BaseArea> subAreas; //子区域集合

    public BaseArea() {
    }

    @Override
    public String toString() {
        return "BaseArea{" +
                "id=" + id +
                ", prevId=" + prevId +
                ", code=" + code +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", subAreas=" + subAreas +
                '}';
    }

    public BaseArea(Integer id, Integer prevId, Integer code, Integer level, String name) {
        this.id = id;
        this.prevId = prevId;
        this.code = code;
        this.level = level;
        this.name = name;
    }

    public BaseArea(Integer code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrevId() {
        return prevId;
    }

    public void setPrevId(Integer prevId) {
        this.prevId = prevId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<BaseArea> getSubAreas() {
        return subAreas;
    }

    public void setSubAreas(List<BaseArea> subAreas) {
        this.subAreas = subAreas;
    }
}
