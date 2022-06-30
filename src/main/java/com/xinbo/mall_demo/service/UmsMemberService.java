package com.xinbo.mall_demo.service;

import com.xinbo.mall_demo.common.api.Result;


/**
 * 会员管理Service
 * @author Xinbo
 */
public interface UmsMemberService {

    /**
     * 生成验证码
     */
    Result generateAuthCode(String phone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    Result verifyAuthCode(String phone, String autoCode);

}
