package com.example.projet_raphael_lelievre.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class VirementAspect {

    private static final org.slf4j.Logger VIREMENT_LOGGER =
        org.slf4j.LoggerFactory.getLogger("VIREMENT_LOGGER");

    @AfterReturning(
        pointcut = "execution(* com.example.projet_raphael_lelievre.service.CompteService.virement(..))"
    )
    public void logVirement(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();

        Long source = (Long) args[0];
        Long destination = (Long) args[1];
        Double montant = (Double) args[2];

        VIREMENT_LOGGER.info(
            "Virement effectué - Source: {}, Destination: {}, Montant: {}",
            source, destination, montant
        );
    }
}