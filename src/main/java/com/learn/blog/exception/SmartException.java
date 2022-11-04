package com.learn.blog.exception;

import com.learn.blog.enums.ResponseCode;
import lombok.Getter;

/**
 * smart_blog项目中自定义异常
 */
@Getter
public class SmartException extends RuntimeException {
    private final ResponseCode responseCode;

    public SmartException(ResponseCode responseCode, String message) {
        super(message);
        this.responseCode = responseCode;
    }

    public SmartException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.responseCode = responseCode;
    }
}
