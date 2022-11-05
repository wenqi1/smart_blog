package com.learn.blog.model;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.utils.MessageUtils;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

/**
 * 基础响应类
 */
@Getter
@Setter
public class BasicResponse {
    private String code;

    private String msg;

    public BasicResponse(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = MessageUtils.getMessage(responseCode.getMsg());
    }

    public BasicResponse(ResponseCode responseCode, Object[] objects) {
        this.code = responseCode.getCode();
        String message = MessageUtils.getMessage(responseCode.getMsg());
        this.msg = MessageFormat.format(message, objects);
    }

    public BasicResponse(ResponseCode responseCode, String msg){
        this.code = responseCode.getCode();
        this.msg = msg;
    }
}
