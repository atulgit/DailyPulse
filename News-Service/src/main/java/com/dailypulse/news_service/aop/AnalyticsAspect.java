package com.dailypulse.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/*
Aspect for capturing the user behaviour and generating structured logs.
*/
@Aspect
@Component
public class AnalyticsAspect {
    public static final Logger logger = LoggerFactory.getLogger(AnalyticsAspect.class);

    /*
        An advice that executes after a method returns a value.
     It captures a "view" event and logs it in a structured format.
     @param jointPoint: The join point that represents a method execution.
     @param result: returns the result of method execution.
     */
    @AfterReturning(pointcut = "execution(public * com.news.platform.service.NewsArticleService.getArticle(..))", returning = "result")
    public void LogArticleView(JoinPoint joinPoint, Object result) {
        String userId = "anonymous";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            userId = authentication.getName();
        }

        Long articleId = (Long) joinPoint.getArgs()[0];
        Map<String, Object> logData = new HashMap<>();
        logData.put("event", "article_viewed");
        logData.put("userId", userId);
        logData.put("article_id", articleId);

//        logger.info("User Action Captured", logData);
    }
}
