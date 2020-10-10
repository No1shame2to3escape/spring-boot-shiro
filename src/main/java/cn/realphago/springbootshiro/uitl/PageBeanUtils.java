package cn.realphago.springbootshiro.uitl;

import cn.realphago.springbootshiro.mapper.AbstractMapper;
import cn.realphago.springbootshiro.pojo.PageBean;
import cn.realphago.springbootshiro.pojo.Product;
import cn.realphago.springbootshiro.service.PageBeanService;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/8 18:20
 */
public class PageBeanUtils<T> {

    private final AbstractMapper<T> mapper;

    public PageBeanUtils() {
        mapper = null;
    }

    public PageBeanUtils(AbstractMapper mapper) {
        this.mapper = mapper;
    }

    public PageBean<T> findList(PageBean pageBean) {
        PageBean<T> tPageBean = new PageBean<T>();
        tPageBean.setPageSize(pageBean.getPageSize());
        tPageBean.setCurrentPage(pageBean.getCurrentPage());
        tPageBean.setTotalCount(mapper.findTotalCount(pageBean.getParameterMap()));
        tPageBean.setTotalPage(tPageBean.getTotalCount() % tPageBean.getPageSize() == 0 ? tPageBean.getTotalCount() / tPageBean.getPageSize() : tPageBean.getTotalCount() / tPageBean.getPageSize() + 1);
        if (pageBean.getCurrentPage() > tPageBean.getTotalPage()) {
            pageBean.setCurrentPage(tPageBean.getTotalPage());
            tPageBean.setCurrentPage(tPageBean.getTotalPage());
        }
        pageBean.setPageStart((pageBean.getCurrentPage() - 1) * pageBean.getPageSize());
        tPageBean.setPageStart((pageBean.getCurrentPage() - 1) * pageBean.getPageSize());
        tPageBean.setElements(mapper.findList(pageBean));
        tPageBean.setParameterMap(pageBean.getParameterMap());
        return tPageBean;
    }

    public PageBean<T> findAll(Integer currentPage, Integer pageSize, PageBeanService<T> service, Map<String, Object> parameterMap) {
        PageBean parameterPageBean = new PageBean();
        if (StringUtils.isEmpty(currentPage) || currentPage <= 0) {
            currentPage = 1;
        }
        parameterPageBean.setCurrentPage(currentPage);
        if (StringUtils.isEmpty(pageSize) || pageSize <= 0) {
            pageSize = 5;
        }
        parameterPageBean.setPageSize(pageSize);
        if (parameterMap != null) {
            parameterPageBean.setParameterMap(parameterMap);
        }
        return service.findList(parameterPageBean);
    }

}
