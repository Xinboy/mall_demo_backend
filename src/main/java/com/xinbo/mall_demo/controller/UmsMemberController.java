package com.xinbo.mall_demo.controller;

import com.xinbo.mall_demo.common.api.Result;
import com.xinbo.mall_demo.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 会员管理 Controller
 * @author Xinbo
 */
@Api(tags = "会员登录注册管理")
@Controller
@RequestMapping("/sso")
public class UmsMemberController {

    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("获取验证码")
    @GetMapping(value = "/getAuthCode")
    @ResponseBody
    public Result getAuthCode(@RequestParam String phone) {
        return memberService.generateAuthCode(phone);
    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping(value = "/verifyAuthCode")
    @ResponseBody
    public Result verifyAuthCode(@RequestParam String phone,
                                 @RequestParam String authCode) {
        return memberService.verifyAuthCode(phone, authCode);
    }
}
