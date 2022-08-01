package com.xinbo.mall_demo.controller;


import com.xinbo.mall_demo.common.api.Result;
import com.xinbo.mall_demo.model.elasticsearch.EsProduct;
import com.xinbo.mall_demo.service.EsProductService;
import com.xinbo.mall_demo.common.api.CommonPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xinbo
 */
@Api(tags = "搜索商品管理", value = "EsProductController")
@Controller
@RequestMapping("/esProduct")
@ResponseBody
public class EsProductController {
    @Autowired
    private EsProductService productService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @PostMapping(value = "/product")
    public Result<Integer> importAllList() {
        int count = productService.importAll();
        return Result.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @DeleteMapping(value = "/product/{id}")
    public Result<Object> delete(@PathVariable Long id) {
        productService.delete(id);
        return Result.success(null);
    }

    @ApiOperation(value = "根据ids 批量删除商品")
    @DeleteMapping(value = "/product")
    public Result<Object> delete(@RequestParam("ids") List<Long> ids) {
        productService.delete(ids);
        return Result.success(null);
    }

    @ApiOperation(value = "根据id 创建商品")
    @PostMapping(value = "/product/{id}")
    public Result<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = productService.create(id);
        if (esProduct != null) {
            return Result.success(esProduct);
        } else {
            return Result.failed();
        }
    }

    @ApiOperation(value = "简单搜索")
    @GetMapping(value = "/product")
    public Result<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                @RequestParam(required = false, defaultValue = "5") Integer pageSize)  {
        Page<EsProduct> esProductPage = productService.search(keyword, pageNum, pageSize);
        return Result.success(CommonPage.restPage(esProductPage));
    }

}
