package com.learn.blog.model;

import com.learn.blog.enums.ResponseCode;
import com.learn.blog.utils.MessageUtils;

/**
 * 基础响应类
 */
public class BasicResponse {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;

    public BasicResponse(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.msg = MessageUtils.getMessage(responseCode.getMsg());
    }


}
