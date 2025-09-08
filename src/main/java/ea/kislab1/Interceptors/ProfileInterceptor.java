package ea.kislab1.Interceptors;

import ea.kislab1.Component.AppStart;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class ProfileInterceptor {
    @Pointcut("within(ea.kislab1.Component.AppStart)")
    public void logInfo() {
    }

    @After("logInfo()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        log.info("название метода: {}", methodName);
        AppStart appStart = (AppStart) jp.getTarget();
        String serviceName = appStart.getServiceName();
        log.info("название метода округления: {}",serviceName);

        appStart.getIntegerList().forEach(val -> log.info("значение: {}", val));
    }

    @Around("logInfo()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info(joinPoint.getSignature() + " выполнен за " + executionTime + "мс");
        return proceed;
    }
}