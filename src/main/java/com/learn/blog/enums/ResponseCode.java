package com.learn.blog.enums;

public enum ResponseCode {
    SUCCESS("0000", "smart_success"),
    FAILURE("1111", "smart_failed");

    private final String code;
    private final String msg;

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
