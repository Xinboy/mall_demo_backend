package com.xinbo.mall_demo.service.impl;

import com.xinbo.mall_demo.common.api.Result;
import com.xinbo.mall_demo.service.RedisService;
import com.xinbo.mall_demo.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * 用户会员 Service 实现类
 * @author Xinbo
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public Result generateAuthCode(String phone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + phone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+phone, AUTH_CODE_EXPIRE_SECONDS);
        return Result.success(sb.toString(), "获取验证码成功");
    }

    @Override
    public Result verifyAuthCode(String phone, String autoCode) {
        if (StringUtils.isEmpty(autoCode)) {
            return Result.failed("请输入验证码");
        }
        String key = REDIS_KEY_PREFIX_AUTH_CODE + phone;
        if (!redisService.hasKey(key)) {
            // 验证码过期
            return Result.failed("验证码不正确");
        }
        String realAutoCode = redisService.get(key).toString();
        boolean result = autoCode.equals(realAutoCode);
        if (result) {
            // 验证成功后,删除该验证码(只验证一次)
            redisService.del(key);
            return Result.success(null, "验证码校验成功");
        } else {
            return Result.failed("验证码不正确");
        }
    }
}
