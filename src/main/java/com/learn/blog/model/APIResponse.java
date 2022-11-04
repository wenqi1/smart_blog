package com.learn.blog.model;

import com.learn.blog.enums.ResponseCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 带返回数据的响应类
 * @param <T>
 */
@Getter
@Setter
public class APIResponse<T> extends BasicResponse{
    private T data;

    public APIResponse(ResponseCode responseCode, T data) {
        super(responseCode);
        this.data = data;
    }
}
