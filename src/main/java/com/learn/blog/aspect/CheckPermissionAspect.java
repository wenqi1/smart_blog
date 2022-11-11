package com.learn.blog.aspect;

import com.learn.blog.annotation.CheckPermission;
import com.learn.blog.config.JwtConfig;
import com.learn.blog.enums.PermissionNames;
import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

/**
 * 用户登录检查，权限检查
 */
@Aspect
@Component
public class CheckPermissionAspect implements Ordered {
    @Autowired
    private JwtConfig jwtConfig;

    @Pointcut("@annotation(com.learn.blog.annotation.CheckPermission)")
    public void checkPermission() {}

    @Before("checkPermission()")
    @SuppressWarnings("all")
    public void doBefore(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = (HttpServletRequest) requestAttributes.
                resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String token = httpServletRequest.getHeader("token");

        if (token == null) {
            throw new SmartException(ResponseCode.USER_NOT_LOGIN);
        }

        //获取方法上CheckPermission注解的参数
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上的注解
        CheckPermission checkPermission = method.getAnnotation(CheckPermission.class);
        PermissionNames permissionName = checkPermission.value();
        if (permissionName != null) {
            String resourceCode = permissionName.getName();
            // 解析token
            Claims claims = JwtUtils.parseToken(token, jwtConfig.getSalt());
            // 获取token过期时间
            Date expiration = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            if (expiration.before(now)) {
                throw new SmartException(ResponseCode.TOKEN_EXPIRESD);
            }
            // 获取token中的权限
            List<String> permissions = (List<String>) claims.get(jwtConfig.getPermission());
            if (!permissions.contains(resourceCode)) {
                throw new SmartException(ResponseCode.USER_NOT_PERMISSION, new Object[]{resourceCode});
            }
        }

    }

    @Override
    public int getOrder() {
        return 1;
    }

}
