package com.exam.products.examen.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleNosuchElement(IllegalArgumentException ex, WebRequest request){
      log.error("IllegalArgumentException"+ex.getMessage());
      String body = ex.getMessage();
      return handleExceptionInternal(ex,body, new HttpHeaders(), HttpStatus.BAD_REQUEST,request);

    }

}
