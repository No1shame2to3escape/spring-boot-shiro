package cn.realphago.springbootshiro.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author gaoyizhong
 * @create 2020/08/2020/8/8 20:07
 */
public class PageBean<E> implements Serializable {

    private Integer currentPage;
    private Integer pageStart;
    private Integer pageSize;
    private Integer totalCount;
    private Integer totalPage;
    private List<E> elements;
    private Map<String, Object> parameterMap;

    public PageBean() {
    }

    public PageBean(Integer pageStart, Integer pageSize) {
        this.pageStart = pageStart;
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageStart=" + pageStart +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", elements=" + elements +
                '}';
    }

    public Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<E> getElements() {
        return elements;
    }

    public void setElements(List<E> elements) {
        this.elements = elements;
    }
}
