package com.learn.blog.enums;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS("0000", "smart_success"),
    FAILURE("1111", "smart_failed"),
    PARAM_EXCEPTION("0001","parameter_exception"),
    PHONE_EXIST("0002", "phone_exist"),
    INITIALIZATION_SNOWFLAKE_EXCEPTION("0003", "initialization_snowflake_exception"),
    USER_NOT_EXIST("0004", "user_does_not_exist"),
    PARENT_RESOURCE_NOT_EXIST("0005", "parent_resource_not_exist"),
    ROLE_NOT_EXIST("0006", "role_does_not_exist"),
    RESOURCE_NOT_EXIST("0007", "resource_does_not_exist");

    private final String code;
    private final String msg;

    ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
