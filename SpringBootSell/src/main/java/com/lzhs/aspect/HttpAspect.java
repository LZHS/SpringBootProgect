package com.lzhs.aspect;

import com.alibaba.fastjson.JSON;
import com.lzhs.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/13 : 1:52 PM<br/>
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {
    @Pointcut("execution(public * com.lzhs.controllers..*.*(..))")
    private void log() {
    }

    @Around("log()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String uuid = KeyUtil.genUniqueKey();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("\n\t请求标识: {}\n\t请求IP: {}\n\t请求路径: {}\n\t请求方式: {}\n\t方法描述: {}\n\t请求参数: {}", uuid, request.getRemoteAddr(), request.getRequestURL(), request.getMethod(), getMethodInfo(point), JSON.toJSONString(request.getParameterMap(), true));
        long startTime = System.currentTimeMillis();
        Object[] args = point.getArgs();
        Object retVal = point.proceed(args);
        long endTime = System.currentTimeMillis();
        log.info("\n\t请求标识: {} \n\t执行时间: {} ms \n\t返回值: {}", uuid, endTime - startTime, JSON.toJSONString(retVal, true));
        return retVal;
    }

    private String getMethodInfo(JoinPoint point) {
        String className = point.getSignature().getDeclaringType().getSimpleName();
        String methodName = point.getSignature().getName();
        String[] parameterNames = ((MethodSignature) point.getSignature()).getParameterNames();
        StringBuilder sb = null;
        if (Objects.nonNull(parameterNames)) {
            sb = new StringBuilder();
            for (int i = 0; i < parameterNames.length; i++) {
                String value = point.getArgs()[i] != null ? point.getArgs()[i].toString() : "null";
                sb.append(parameterNames[i] + ":" + value + "; ");
            }
        }
        sb = sb == null ? new StringBuilder() : sb;
        String info = String.format("class_method : %s.%s ; params : %s", className, methodName, sb.toString());
        return info;
    }

}
