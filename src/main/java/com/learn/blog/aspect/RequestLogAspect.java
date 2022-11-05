package com.learn.blog.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 请求记录
 */
@Aspect
@Component
@Slf4j
public class RequestLogAspect {
    // controller层的所有方法
    @Pointcut("execution(public * com.learn.blog.controller..*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> enu = request.getParameterNames();
        StringBuilder parametersBuilder = new StringBuilder();
        while (enu.hasMoreElements()) {
            String name = enu.nextElement();
            String value = request.getParameter(name);
            parametersBuilder.append(name).append(":").append(value).append(",");
        }
        String parameters = parametersBuilder.toString();
        if (parameters.endsWith(",")) {
            parameters = parameters.substring(0, parameters.length() - 1);
        }
        // 记录下请求内容
        log.info("URL: {}", request.getRequestURL().toString());
        log.info("HTTP_METHOD: {}", request.getMethod());
        log.info("PARAMETER: [{}]", parameters);
    }

    @AfterReturning(returning = "ret", pointcut = "log()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("RESPONSE: {}", objectMapper.writeValueAsString(ret));
    }
}
