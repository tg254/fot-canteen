package com.hello.FirstApp.exception;

public class CustomExceptionRequest extends RuntimeException {
    public CustomExceptionRequest(String s) {
        super(s);
    }

    public CustomExceptionRequest(String s, Throwable throwable) {
        super(s, throwable);
    }
}
