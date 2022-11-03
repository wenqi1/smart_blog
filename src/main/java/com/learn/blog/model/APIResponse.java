package com.learn.blog.model;

import com.learn.blog.enums.ResponseCode;

/**
 * 带返回数据的响应类
 * @param <T>
 */
public class APIResponse<T> extends BasicResponse{
    private T data;

    public APIResponse(ResponseCode responseCode, T data) {
        super(responseCode);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
