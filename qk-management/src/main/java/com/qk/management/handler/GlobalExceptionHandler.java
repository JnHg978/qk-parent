package com.qk.management.handler;

import com.qk.common.Result;
import com.qk.exception.CommonBizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    //处理异常
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){//方法形参中指定能够处理的异常类型
        log.error("服务器发生异常", e);
        //捕获到异常之后，响应一个标准的Result
        return Result.error("网络异常, 请稍后重试!");
    }

    @ExceptionHandler(CommonBizException.class)
    public Result handlerCommonBizException(CommonBizException e){
        log.error("业务异常: {}", e.getMessage());
        return Result.error(e.getMessage());
    }
}