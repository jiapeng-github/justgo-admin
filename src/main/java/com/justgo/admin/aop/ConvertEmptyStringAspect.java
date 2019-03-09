package com.justgo.admin.aop;

import com.justgo.admin.utils.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

/**
 * 转换空字符串未null
 */
@Aspect
@Configuration
public class ConvertEmptyStringAspect {

    private static final Logger LOGGER = LogManager.getLogger(ConvertEmptyStringAspect.class);

    @Pointcut("execution(* com.justgo.admin.controller.*Controller.*(..))")
    public void convertController() {
    }


    @Around("convertController()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg == null || filter(arg)) break;
            Field[] declaredFields = arg.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                declaredField.setAccessible(true);
                if (declaredField.getType() == String.class && "".equals(declaredField.get(arg))) {
                    declaredField.set(arg, null);
                }
            }
        }
        //日志跟踪号
        String traceId = "ADMIN_" + DateUtil.getRandomTraceId();//用前缀区分来源(APP/MQ/JOB/ADMIN)
        MDC.put("TRACE_ID",traceId);
//        RpcContext.getContext().getAttachments().put("TRACE_ID",traceId);
        return pjp.proceed(args);
    }


    /**
     * 是否拦截
     *
     * @param obj
     * @return
     */
    private boolean filter(Object obj) {
        return false;
    }


}
