package com.dailypulse.news_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/*
This @ControllerAdvice annotation allows to handle the exception across the entire application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /*
    @return A ResponseEntity with a 404 Not Found status and the exception message.
     */
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<String> handleArticleNotFoundException(ArticleNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
