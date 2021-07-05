package com.hello.FirstApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

public class CustomExceptionHandler {

    @ExceptionHandler(value = {CustomExceptionRequest.class})
    public ResponseEntity<Object> handleRequestExceptions(CustomExceptionRequest req){
        HttpStatus badReq = HttpStatus.BAD_REQUEST;
        CustomException ce = new CustomException(req.getMessage(), badReq, ZonedDateTime.now());

        return new ResponseEntity<>(ce, badReq);
    }
}
