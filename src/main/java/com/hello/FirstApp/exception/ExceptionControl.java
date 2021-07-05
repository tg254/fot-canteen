package com.hello.FirstApp.exception;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionControl {

    Log log = LogFactory.getLog(ExceptionControl.class);

    @ExceptionHandler(value = Exception.class)
    public String handleException(HttpServletRequest req, Exception ex){
        log.error("Request for "+ req + "has thrown an error.\nMoRE:"+ex.toString());
        return "error";
    }
}
