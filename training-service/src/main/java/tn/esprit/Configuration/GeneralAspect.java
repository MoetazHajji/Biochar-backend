package tn.esprit.Configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class GeneralAspect {

    @AfterReturning(" execution(* tn.esprit.Service.*.*(..)) ")
    public void logMethodExitReturn(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("The function " + name + " was executed successfully ");
    }

}
