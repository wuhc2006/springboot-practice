package com.whc.config.aop;

import com.whc.util.ContextUtil;
import com.whc.util.JwtUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 * @date 2019/7/26 22:40
 */
@Aspect
@Component
public class LogAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

    @Pointcut("execution(* com.whc.service..*(..))")
    public void saveLog() {
    }

    /**
     * 注意：必须是有返回值，否则无法执行方法
     *
     * @param point
     * @return
     */
    @Around("saveLog()")
    public Object doBefore(ProceedingJoinPoint point) {
        String method = point.getSignature().getName();
        String clazz = point.getTarget().getClass().getSimpleName();
        MethodSignature ms = (MethodSignature) point.getSignature();//请求参数
        String[] params = ms.getParameterNames();// 参数名
        Object[] values = point.getArgs();
        Object token = ContextUtil.get();
        token = token == null ? null : JwtUtil.getUsername(token.toString());
        logger.info(token + "在" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "执行了" + method + "方法，参数：" + getInvokeInfo(params, values));

        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            logger.error("请求类" + clazz + "的" + method + "出错!", throwable);
        }
        return result;
    }

    /**
     * 获取调用的信息
     *
     * @param params
     * @param values
     * @return
     */
    private String getInvokeInfo(String[] params, Object[] values) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            sb.append(params[i]).append("=").append(values[i]);
            if (i != values.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
