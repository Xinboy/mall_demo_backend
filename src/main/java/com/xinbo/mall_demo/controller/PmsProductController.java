package com.xinbo.mall_demo.controller;

import com.xinbo.mall_demo.common.api.Result;
import com.xinbo.mall_demo.mbg.model.PmsProduct;
import com.xinbo.mall_demo.model.dto.PmsProductQueryParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author xinbo
 */
@Api(tags = "商品管理", value = "PmsProductController")
@RestController
@RequestMapping("/product")
@ResponseBody
public class PmsProductController {

}
