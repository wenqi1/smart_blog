package com.learn.blog.handler;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.BasicResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(SmartException.class);

    @ExceptionHandler(value = SmartException.class)
    @ResponseBody
    public BasicResponse basicException(SmartException e){
        logger.error(e.getMessage());
        return new BasicResponse(ResponseCode.valueOf(e.getErrorCode()));
    }
}
