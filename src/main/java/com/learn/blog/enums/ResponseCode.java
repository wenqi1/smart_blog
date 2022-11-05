package com.learn.blog.enums;

public enum ResponseCode {
    SUCCESS("0000", "smart_success"),
    FAILURE("1111", "smart_failed"),
    PARAM_EXCEPTION("0001","parameter_exception"),
    PHONE_EXIST("0002", "phone_exist"),
    INITIALIZATION_SNOWFLAKE_EXCEPTION("0003", "initialization_snowflake_exception"),
    USER_NOT_EXIST("0004", "the_modified_user_does_not_exist");

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
