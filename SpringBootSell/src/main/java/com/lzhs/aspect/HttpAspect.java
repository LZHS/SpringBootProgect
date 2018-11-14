package com.lzhs.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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

    @Before("log()")
    private void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        JSONObject logJson = new JSONObject();
        logJson.put("IP", request.getRemoteAddr());
        logJson.put("URL", request.getRequestURL().toString());
        logJson.put("METHOD", request.getMethod());
        logJson.put("CLASS_METHOD", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logJson.put("PARAMS", joinPoint.getArgs());

        log.info("requestBefore = {}", logJson);

    }

    @AfterReturning(returning = "obj", pointcut = "log()")
    private void afterReturning(Object obj) {
        log.info("ResponsBody = {}", JSON.toJSONString(obj));

    }
}
