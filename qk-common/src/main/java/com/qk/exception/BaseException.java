package com.qk.exception;

/**
 * @Author: hjh
 * @Date: 2025/10/10 16:20
 * @Description:
 */
public class BaseException extends RuntimeException{
    public BaseException() {
        super();
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
