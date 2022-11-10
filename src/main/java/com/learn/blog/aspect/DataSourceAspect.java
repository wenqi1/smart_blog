package com.learn.blog.aspect;

import com.learn.blog.annotation.DataSourceSwitch;
import com.learn.blog.multisource.DynamicDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 处理DataSourceSwitch注解，实现动态切换数据源
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered {

    @Pointcut("@annotation(com.learn.blog.annotation.DataSourceSwitch)")
    public void dataSourcePointCut() {};

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上指定的注解
        DataSourceSwitch dataSourceSwitch = method.getAnnotation(DataSourceSwitch.class);
        DynamicDataSource.setDataSource(dataSourceSwitch.name());

        try {
            return point.proceed();
        } finally {
            // 最后清空
            DynamicDataSource.clearDataSource();
        }

    }

    @Override
    public int getOrder() {
        return 2;
    }
}
