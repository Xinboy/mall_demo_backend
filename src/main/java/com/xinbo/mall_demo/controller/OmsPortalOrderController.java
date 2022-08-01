package com.xinbo.mall_demo.controller;

import com.xinbo.mall_demo.common.api.Result;
import com.xinbo.mall_demo.model.dto.OrderParam;
import com.xinbo.mall_demo.service.OmsPortalOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 订单管理Controller
 * @author xinbo
 */
@Api(tags = "订单管理", value = "OmsPortalOrderController")
@RestController
@RequestMapping("/order")
@ResponseBody
public class OmsPortalOrderController {

    @Autowired
    private OmsPortalOrderService portalOrderService;
    @ApiOperation("根据购物车信息生成订单")
    @PostMapping(value = "")
    @ResponseBody
    public Result generateOrder(@RequestBody OrderParam orderParam) {
        Map<String, Object> result = (Map<String, Object>) portalOrderService.generateOrder(orderParam);
        return Result.success(result, "下单成功");
    }
}
