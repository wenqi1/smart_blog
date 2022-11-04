package com.learn.blog.handler;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.exception.SmartException;
import com.learn.blog.model.BasicResponse;
import com.learn.blog.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = SmartException.class)
    @ResponseBody
    public BasicResponse basicExceptionHandle(SmartException exception) {
        log.error(exception.getMessage());
        return new BasicResponse(exception.getResponseCode());
    }

    /**
     * 统一处理入参异常场景
     *
     * @param exception BindException and MethodArgumentNotValidException
     * @return BasicResponse
     */
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    public BasicResponse validationExceptionHandle(Exception exception) {
        log.error(exception.getMessage());
        BindingResult bindingResult = null;
        if (exception instanceof  BindException) {
            bindingResult = ((BindException) exception).getBindingResult();
        }
        if (exception instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        // 对错误参数的拼接
        StringBuilder errorMessage = new StringBuilder();
        if (bindingResult != null && bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                String field = fieldError.getField();
                String message = MessageUtils.getMessage(ResponseCode.PARAM_EXCEPTION.getMsg());
                errorMessage.append(MessageFormat.format(message, field)).append(",");
            });
        }
        String msg = errorMessage.toString();
        if (msg.endsWith(",")) {
            msg = msg.substring(0, msg.length() - 1);
        }
        return new BasicResponse(ResponseCode.PARAM_EXCEPTION, msg);
    }

}
