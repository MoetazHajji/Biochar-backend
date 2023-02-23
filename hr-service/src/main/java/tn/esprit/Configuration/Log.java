package tn.esprit.Configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class Log {

    @AfterReturning(" execution(* tn.esprit.Service.*.*(..)) ")
    public void logMethodExitReturning(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("Successful Execution" + name + " : ");
    }
}
