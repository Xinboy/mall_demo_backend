package com.xinbo.mall_demo.common.exception;

import com.xinbo.mall_demo.common.api.IErrorCode;
import net.bytebuddy.implementation.bytecode.Throw;

/**
 * 自定义API异常
 * @author Xinbo
 */

public class ApiException extends RuntimeException{
    private IErrorCode errorCode;

    public ApiException(IErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }
}
