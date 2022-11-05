package com.learn.blog.exception;

import com.learn.blog.enums.ResponseCode;
import lombok.Getter;

/**
 * smart_blog项目中自定义异常
 */
@Getter
public class SmartException extends RuntimeException {
    private final ResponseCode responseCode;

    private Object[] data;

    private Exception exception;

    public SmartException(ResponseCode responseCode, Object[] data) {
        super(responseCode.getMsg());
        this.responseCode = responseCode;
        this.data = data;
    }

    public SmartException(ResponseCode responseCode, Exception e) {
        super(responseCode.getMsg());
        this.responseCode = responseCode;
        this.exception = e;
    }

    public SmartException(ResponseCode responseCode) {
        super(responseCode.getMsg());
        this.responseCode = responseCode;
    }
}
