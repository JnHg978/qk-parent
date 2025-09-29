package com.qk.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: hjh
 * @Date: 2025/9/27 9:25
 * @Description: 统一响应结果类
 */

@Data
public class Result implements Serializable {

    private Integer code; //编码：1成功，0为失败
    private String msg; //错误信息
    private Object data; //数据

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}