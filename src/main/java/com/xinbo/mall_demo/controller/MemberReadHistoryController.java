package com.xinbo.mall_demo.controller;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import com.xinbo.mall_demo.common.api.Result;
import com.xinbo.mall_demo.model.mongodb.document.MemberReadHistory;
import com.xinbo.mall_demo.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员商品浏览记录管理Controller
 * @author xinbo
 */
@Api(tags = "会员商品浏览记录管理", value = "MemberReadHistoryController")
@RestController
@RequestMapping("/member/readHistory")
@ResponseBody
public class MemberReadHistoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberReadHistoryController.class);

    @Autowired
    private MemberReadHistoryService service;

    @ApiOperation(value = "创建浏览记录")
    @PostMapping(value = "/create")
    public Result create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = service.create(memberReadHistory);
        if (count > 0) {
            return Result.success(count);
        }
        return  Result.failed();
    }

    @ApiOperation(value = "删除浏览记录")
    @DeleteMapping(value = "/delete")
    public Result delete(@RequestParam("ids") List<String> ids) {
        int count = service.delete(ids);
        if (count > 0) {
            return Result.success(count);
        }
        return  Result.failed();
    }

    @ApiOperation(value = "删除指定用户的浏览记录")
    @DeleteMapping(value = "/delete/{memberId}")
    public Result delete(@PathVariable Long memberId) {
        int count =  service.clear(memberId);
        if (count > 0) {
            return Result.success(count);
        }
        return  Result.failed();
    }

    @ApiOperation(value = "删除全部浏览记录")
    @DeleteMapping(value = "/delete/all")
    public Result delete() {
        int count = service.clear();
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation(value = "获取浏览记录")
    @GetMapping("/{id}")
    public Result<List<MemberReadHistory>> list(@PathVariable Long id) {
        List<MemberReadHistory> list = service.list(id);
        return Result.success(list);
    }


}
