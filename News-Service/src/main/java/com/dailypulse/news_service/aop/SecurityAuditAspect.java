package com.dailypulse.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;


/*
Aspect for security related audits and capturing security events like failed login attempts
and unauthorized access attemps.
 */
@Aspect
@Component
public class SecurityAuditAspect {

    private static Logger logger = LoggerFactory.getLogger(SecurityAuditAspect.class);

    /*
        An advice for logging failed login attempts. It intercepts a method that throws a BadCredentialsException.
     */
    @AfterThrowing(pointcut = "execution(* org.springframework.security.authentication.AuthenticationManager.authenticate(..)) && args(authentication,..)", throwing = "ex")
    public void logFailedLogin(JoinPoint joinPoint, Exception ex, UsernamePasswordAuthenticationToken token) {
        if (ex instanceof BadCredentialsException) {
            Map<String, Object> logData = new HashMap<>();
            logData.put("event", "login_failed");
            logData.put("username", token.getName());
            logData.put("message", "Incorrect username or password");
            logger.warn("Security Audit Event: Login failed", logData);
        }
    }

    /*
    An advice that logs the unahorized access attempt. It intercepts the method that throws an AccessDeniedException.
     */
    @AfterThrowing(pointcut = "")
    public void logUnAuthorizedAccess(JoinPoint joinPoint, AccessDeniedException ex) {
        String username = "anonymous";
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        }

        Map<String, Object> logData = new HashMap<>();
        logData.put("event", "unauthorized_access");
        logData.put("username", username);
        logData.put("endpoint", joinPoint.getSignature().toShortString());
        logData.put("message", "User attempted to access a protected resource without proper authorization.");
        logger.warn("Security Audit Event: Unauthorized access", logData);
    }
}
