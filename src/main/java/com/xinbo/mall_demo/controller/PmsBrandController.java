package com.xinbo.mall_demo.controller;

import com.xinbo.mall_demo.common.api.Page;
import com.xinbo.mall_demo.common.api.Result;
import com.xinbo.mall_demo.mbg.model.PmsBrand;
import com.xinbo.mall_demo.service.PmsBrandService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  商品品牌管理 Controller
 * @author Xinbo
 */
@RestController
@RequestMapping("/brand")
@Api(tags = "商品品牌管理")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @GetMapping(value = "/all")
    public Result<List<PmsBrand>> getBrandList() {
        return Result.success(pmsBrandService.listAll());
    }

    @GetMapping(value = "/{id}")
    public Result<PmsBrand> getBrand(@PathVariable Long id) {
        return Result.success(pmsBrandService.get(id));
    }

    @GetMapping()
    public Result<Page<PmsBrand>> getBrandLists(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        List<PmsBrand> list = pmsBrandService.list(pageNum, pageSize);
        return Result.success(Page.restPage(list));
    }

    @PostMapping()
    public Result create(@RequestBody PmsBrand pmsBrand) {
        Result result;
        int count = pmsBrandService.create(pmsBrand);
        if (count == 1) {
            result = Result.success(pmsBrand);
            LOGGER.debug("createBrand success:{}",pmsBrand);
        } else {
            result = Result.failed("操作失败");
            LOGGER.debug("createBrand failed:{}",pmsBrand);
        }
        return result;
    }

    @PutMapping(value = "/{id}")
    public Result update(@PathVariable Long id, @RequestBody PmsBrand pmsBrand, BindingResult result) {
        Result res;
        int count = pmsBrandService.update(id, pmsBrand);
        if (count == 1) {
            res = Result.success(pmsBrand);
            LOGGER.debug("updateBrand success:{}",pmsBrand);
        } else {
            res = Result.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}",pmsBrand);
        }
        return res;
    }

    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Long id) {
        Result res;
        int count = pmsBrandService.delete(id);
        if (count == 1) {
            res = Result.success(null);
            LOGGER.debug("updateBrand success: id={}", id);
        } else {
            res = Result.failed("操作失败");
            LOGGER.debug("updateBrand failed: id={}",id);
        }
        return res;
    }

}
