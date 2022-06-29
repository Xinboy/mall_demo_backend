package com.xinbo.mall_demo.common.api;

/**
 * 常用API返回对象接口
 * @author Xinbo
 */
public interface IErrorCode {

    /**
     * @return 错误码
     */
    long getCode();

    /**
     * @return 错误信息
     */
    String getMessage();
}
