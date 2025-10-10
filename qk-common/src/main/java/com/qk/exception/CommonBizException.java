package com.qk.exception;

import com.qk.enums.Code;
import lombok.Getter;

/**
 * @Author: hjh
 * @Date: 2025/10/10 16:20
 * @Description:
 */
@Getter
public class CommonBizException extends BaseException {

    private CommonBizException(Code code) {
        super(code.getMsg());
    }

    public static void throwException(Code code) {
        throw new CommonBizException(code);
    }

}
