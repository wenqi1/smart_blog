package com.learn.blog.exception;

import lombok.Getter;

/**
 * smart_blog项目中自定义异常
 */
@Getter
public class SmartException extends RuntimeException {
    private final String errorCode;

    public SmartException(String errorCode) {
        this.errorCode = errorCode;
    }
}
