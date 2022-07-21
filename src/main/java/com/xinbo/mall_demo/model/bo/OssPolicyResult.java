package com.xinbo.mall_demo.model.bo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取OSS上传文件授权返回结果
 * @author xinbo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OssPolicyResult {
    @ApiModelProperty("访问身份验证中用到的用户标识")
    private String accessKeyId;
    @ApiModelProperty("用户表单上传策略, 经过base64加密")
    private String policy;
    @ApiModelProperty("对policy签名后的字符串")
    private String signature;
    @ApiModelProperty("上传文件夹路径前缀")
    private String dir;
    @ApiModelProperty("oss对外服务的访问域名")
    private String host;
    @ApiModelProperty("上传成功后的回调设置")
    private String callback;
}
