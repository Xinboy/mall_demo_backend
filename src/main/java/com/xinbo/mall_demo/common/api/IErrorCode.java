package com.xinbo.mall_demo.common.api;

/**
 * 常用API返回对象接口
 * @author Xinbo
 */
public interface IErrorCode {

    /**
     * 返回码
     * @return 错误码
     */
    long getCode();

    /**
     * 返回信息
     * @return 返回信息
     */
    String getMessage();
}
