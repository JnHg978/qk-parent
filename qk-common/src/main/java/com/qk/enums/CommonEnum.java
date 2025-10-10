package com.qk.enums;

/**
 * @Author: hjh
 * @Date: 2025/10/10 16:16
 * @Description:
 */
public enum CommonEnum implements  Code{
    PARAM_ERROR(1001, "参数错误"),
    USERNAME_PASSWORD_ERROR(1002, "账号/密码错误"),
    USER_IS_EXIST(1003, "用户存在"),
    USER_DEACTIVATED(1004, "用户已停用"),
    ;

    private final Integer code;
    private final String msg;

    CommonEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
