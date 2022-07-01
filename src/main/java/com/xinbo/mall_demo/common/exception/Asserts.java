package com.xinbo.mall_demo.common.exception;

import com.xinbo.mall_demo.common.api.IErrorCode;

/**
 * 断言处理类，用于抛出各种API异常
 * @author Xinbo
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
