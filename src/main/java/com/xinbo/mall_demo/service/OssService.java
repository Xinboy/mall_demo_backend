package com.xinbo.mall_demo.service;

import com.xinbo.mall_demo.model.bo.OssCallbackResult;
import com.xinbo.mall_demo.model.bo.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xinbo
 */
public interface OssService {

    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
