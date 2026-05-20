package com.celivra.bbs.Common;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.code = 200;
        result.message = "success";
        result.data = data;
        return result;
    }
    public static <T> Result<T> fail(String message) {
        Result<T> result = new Result<T>();
        result.message = message;
        result.code = 400;
        result.data = null;
        return result;
    }
}
