package cn.realphago.springbootshiro.config;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.GlobalInfoUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/6 14:25
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    @Scheduled(fixedRate = 60000)
    private void configureTasks() {
        Map<HttpSession, Date> userLastOperationTimeMap = GlobalInfoUtils.getUserLastOperationTimeMap();
        if (userLastOperationTimeMap.size() != 0) {
            Set<HttpSession> httpSessions = userLastOperationTimeMap.keySet();
            for (HttpSession httpSession : httpSessions) {
                if (new Date().after(new Date(userLastOperationTimeMap.get(httpSession).getTime() + 300000))) {
                    GlobalInfoUtils.removeOperationMap(httpSession);
                    httpSession.invalidate();
                }
            }
        }
    }

}
