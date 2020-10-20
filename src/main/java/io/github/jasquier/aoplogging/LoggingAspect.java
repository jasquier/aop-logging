package io.github.jasquier.aoplogging;

import static java.lang.String.format;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

  // AOP expression for which methods shall be intercepted
  @Around("execution(* io.github.jasquier.aoplogging.service..*(..)))")
  public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

    // Get intercepted method details
    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();

    final StopWatch stopWatch = new StopWatch();

    // Measure method execution time
    stopWatch.start();
    Object result = proceedingJoinPoint.proceed();
    stopWatch.stop();

    // Log method execution time
    String timingInfo = format("Execution time of %s.%s::%d ms", className, methodName,
        stopWatch.getTotalTimeMillis());
    log.info(timingInfo);

    // Log argument info
    Object[] args = proceedingJoinPoint.getArgs();
    log.info(format("Number of args = %d", args.length));
    if (args.length == 1 && args[0] instanceof Long) {
      log.info(format("Called with: %d", (Long) args[0]));
    }

    // TODO Log return value

    return result;
  }
}
