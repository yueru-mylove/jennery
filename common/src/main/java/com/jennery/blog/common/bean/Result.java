package com.jennery.blog.common.bean;

import com.jennery.blog.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> {

    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ErrorCode.SUCCESS.getCode());
        result.setMsg(ErrorCode.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }


    public static <T> Result<T> failed(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(message);
        return result;
    }
}
