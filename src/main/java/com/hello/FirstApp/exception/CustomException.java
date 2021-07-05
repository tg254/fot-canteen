package com.hello.FirstApp.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class CustomException {

    final String message;
    final HttpStatus httpStatus;
    final ZonedDateTime zonedDateTime;

    public CustomException(String message,  HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }

}
