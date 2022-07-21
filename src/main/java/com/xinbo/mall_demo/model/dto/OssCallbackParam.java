package com.xinbo.mall_demo.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * OSS 上传回调参数
 * @author xinbo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OssCallbackParam {
    @ApiModelProperty("请求的回调路径")
    private String callbackUrl;
    @ApiModelProperty("回调时传入request的参数")
    private String callbackBody;
    @ApiModelProperty("回调时传入参数的格式,比如表单提交格式")
    private String callbackBodyType;
}
