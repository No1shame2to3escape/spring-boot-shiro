package cn.realphago.springbootshiro.aspect;

import cn.realphago.springbootshiro.uitl.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author gaoyizhong
 * @create 2020/10/2020/10/14 21:41
 */
@Component
@Aspect
public class SqlExceptionHandleAspect {

    @Pointcut(value = "execution(boolean cn.realphago.springbootshiro.service..*.create (..))")
    public void insertPointCut() {
    }

    @AfterThrowing(pointcut = "insertPointCut()")
    public void createExceptionHandle(JoinPoint joinPoint) {
        System.out.println("=============================");
        System.out.println("错误在这里,  " + DateFormatUtils.format(new Date()) + "类名" + joinPoint.getTarget().getClass() + ", 方法名 = " + joinPoint.getSignature().getName());
        System.out.println("==================================");
    }


}
