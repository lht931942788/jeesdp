package cn.org.rookie.jeesdp.common.component;

import cn.org.rookie.jeesdp.common.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(cn.org.rookie.jeesdp.common.annotation.Log)")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) throws Throwable {

        // 首先获取方法的签名，joinPoint中有相应的签名信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 通过方法的签名可以获取方法本身
        Method method = signature.getMethod();
        // 通过joinPoint获取方法的实际参数的值
        Object[] args = joinPoint.getArgs();
        // 最后如果校验通过则调用proceed方法进行方法的实际执行
    }

    @Around("log()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = RequestUtils.getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keys = parameterMap.keySet();
        StringBuilder params = new StringBuilder();
        for (String key : keys) {
            params.append(key).append("=").append(Arrays.toString(parameterMap.get(key))).append(" ");
        }
        // 首先获取方法的签名，joinPoint中有相应的签名信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 通过方法的签名可以获取方法本身
        Method method = signature.getMethod();
        Log log = method.getAnnotation(Log.class);
        logger.debug("==>  type:" + log.type() + ",name:" + log.value() + ",params: " + params);
        // 通过joinPoint获取方法的实际参数的值
        Object[] args = joinPoint.getArgs();
        // 最后如果校验通过则调用proceed方法进行方法的实际执行
        return joinPoint.proceed();
    }
}
