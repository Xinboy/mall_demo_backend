package com.xinbo.mall_demo.controller;

import com.xinbo.mall_demo.common.api.Result;
import com.xinbo.mall_demo.model.bo.OssCallbackResult;
import com.xinbo.mall_demo.model.bo.OssPolicyResult;
import com.xinbo.mall_demo.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xinbo
 */
@Api(tags = "OssController", value = "Oss管理")
@RestController
@RequestMapping("/aliyun/oss")
@ResponseBody
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation(value = "Oss上传签名生成")
    @GetMapping(value = "/policy")
    @ResponseBody
    public Result<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policy();
        return Result.success(result);
    }

    @ApiOperation(value = "Oss上传成功回调")
    @PostMapping(value = "callback")
    @ResponseBody
    public Result<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return Result.success(ossCallbackResult);
    }

}
