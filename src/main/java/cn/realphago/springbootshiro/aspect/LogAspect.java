package cn.realphago.springbootshiro.aspect;

import cn.realphago.springbootshiro.controller.IndexController;
import cn.realphago.springbootshiro.controller.LogController;
import cn.realphago.springbootshiro.pojo.LoginLog;
import cn.realphago.springbootshiro.pojo.SysLog;
import cn.realphago.springbootshiro.service.LogService;
import cn.realphago.springbootshiro.service.LoginLogService;
import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import cn.realphago.springbootshiro.uitl.GlobalInfoUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/**
 * @author gaoyizhong
 * @create 2020/09/2020/9/16 15:31
 */
@Component
@Aspect
public class LogAspect {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogService logService;
    @Autowired
    private LoginLogService loginLogService;

    private Date startTime;//访问时间
    private Class executionClass;//访问的类
    private Method executionMethod;//访问的方法

    @Before("execution(* cn.realphago.springbootshiro.controller.*.*(..))")
    public void beforeLog(JoinPoint joinPoint) throws NoSuchMethodException {
        startTime = new Date();//获取访问时间

        executionClass = joinPoint.getTarget().getClass();//获取访问的类

        //获取访问方法
        String methodName = joinPoint.getSignature().getName();//获取访问方法名
        Object[] args = joinPoint.getArgs();//获取访问方法参数
        if (args == null || args.length == 0) {
            executionMethod = executionClass.getMethod(methodName);//获取无参数访问方法
        } else {
            //有参数，将args中所有元素遍历，获取对应的class，放入到class[]中
            Class[] classeArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classeArgs[i] = args[i].getClass();
                if (classeArgs[i] == BindingAwareModelMap.class) {
                    classeArgs[i] = Model.class;
                }
                if (classeArgs[i] == ShiroHttpServletRequest.class) {
                    classeArgs[i] = HttpServletRequest.class;
                }
            }
            executionMethod = executionClass.getMethod(methodName, classeArgs);
        }
    }

    @After("execution(* cn.realphago.springbootshiro.controller.*.*(..))")
    public void afterLog(JoinPoint joinPoint) {

        //获取类上的RequestMapping
        if (executionClass != LogController.class && executionClass != IndexController.class) {
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);

            //获取方法上的RequestMapping
            if (classAnnotation != null) {
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                GetMapping getMapping = null;
                PostMapping postMapping = null;
                int flag = 0;
                if (methodAnnotation == null) {
                    getMapping = executionMethod.getAnnotation(GetMapping.class);
                    flag = 1;
                }
                if (getMapping == null && methodAnnotation == null) {
                    postMapping = executionMethod.getAnnotation(PostMapping.class);
                    flag = 2;
                }

                SysLog sysLog = new SysLog();
                sysLog.setId(UUID.randomUUID().toString().replace("-", ""));

                //获取访问地址
                String url = "";
                switch (flag) {
                    case 0:
                        url = classAnnotation.value()[0] + methodAnnotation.value()[0];
                        break;
                    case 1:
                        url = classAnnotation.value()[0] + getMapping.value()[0];
                        break;
                    case 2:
                        url = classAnnotation.value()[0] + postMapping.value()[0];
                        break;
                    default:
                        return;
                }
                sysLog.setUrl(url);

                //获取访问时长
                Long executionTime = new Date().getTime() - startTime.getTime();
                sysLog.setExecutionTime(executionTime);
                sysLog.setVisitTime(startTime);

                //获取访问ip
                String ip = request.getRemoteAddr();
                sysLog.setIp(ip);

                //获取访问者
                String username = (String) SecurityUtils.getSubject().getPrincipal();
                sysLog.setUsername(username);

                sysLog.setMethod("[类名]" + executionClass.getName() + "[方法名]" + executionMethod.getName());

                if (!"/user/login".equals(sysLog.getUrl())) {
                    logService.create(sysLog);
                }
                if (!"/user/logout".equals(sysLog.getUrl()) && !"/user/login".equals(sysLog.getUrl())) {
                    GlobalInfoUtils.updateOperationMap(request.getSession(), new Date());
                }


            }
        }
    }

}
