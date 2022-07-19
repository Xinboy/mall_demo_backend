package com.xinbo.mall_demo.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.xinbo.mall_demo.common.api.CommonPage;
import com.xinbo.mall_demo.common.api.Result;
import com.xinbo.mall_demo.mbg.model.UmsAdmin;
import com.xinbo.mall_demo.mbg.model.UmsRole;
import com.xinbo.mall_demo.model.dto.UmsAdminLoginParam;
import com.xinbo.mall_demo.model.dto.UmsAdminParam;
import com.xinbo.mall_demo.model.dto.UpdateAdminPasswordParam;
import com.xinbo.mall_demo.service.UmsAdminService;
import com.xinbo.mall_demo.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台用户管理
 * @author Xinbo
 */
@Api(tags = "后台用户管理", value = "UmsAdminController")
@RestController
@RequestMapping("/admin")
@ResponseBody
public class UmsAdminController {
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleService roleService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public Result<UmsAdmin> register(@RequestBody UmsAdminParam umsAdminParam, BindingResult result) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return Result.failed("注册失败");
        }
        return Result.success(umsAdmin);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping(value ="/login")
    public Result login(@RequestBody UmsAdminLoginParam loginParam, BindingResult result) {
        String token = adminService.login(loginParam.getUsername(), loginParam.getPassword());
        if (token == null) {
            return Result.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Result.success(tokenMap);
    }

    @ApiOperation(value = "刷新 token")
    @GetMapping(value = "/refreshToken")
    public Result refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (StrUtil.isEmpty(refreshToken)) {
            return Result.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return Result.success(tokenMap);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/info")
    public Result getAdminInfo(Principal principal) {
        if (principal == null) {
            return Result.unauthorized(null);
        }
        String username = principal.getName();
        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
        if (CollUtil.isNotEmpty(roleList)) {
            List<String > roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles",roles);
        }
        return Result.success(data);
    }

    @ApiOperation(value = "登出")
    @PostMapping(value = "/logout")
    public Result logout() {
        return null;
    }

    @ApiOperation(value = "根据用户名或姓名分页获取用户列表")
    @GetMapping(value = "/list")
    public Result<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> admins = adminService.list(keyword, pageSize, pageNum);
        return Result.success(CommonPage.restPage(admins));
    }

    @ApiOperation(value = "获取指定用户信息")
    @GetMapping(value = "/{id}")
    public Result<UmsAdmin> getAdmin(@PathVariable Long id) {
        UmsAdmin admin = adminService.getAdmin(id);
        return Result.success(admin);
    }

    @ApiOperation(value = "更新指定用户信息")
    @PutMapping(value = "/{id}")
    public Result updateAdmin(@PathVariable Long id,
                              @RequestBody UmsAdmin admin) {
        int count = adminService.update(id, admin);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation(value = "删除指定用户信息")
    @DeleteMapping(value = "/{id}")
    public Result deleteAdmin(@PathVariable Long id) {
        int count = adminService.delete(id);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation(value = "修改指定用户密码")
    @PostMapping("/updatePwd")
    public Result updatePwd(@Validated  @RequestBody UpdateAdminPasswordParam updatePasswordParam) {
        int status = adminService.updatePassword(updatePasswordParam);
        if (status > 0) {
            return Result.success(status);
        }
        //修改失败的各种状态
        switch (status) {
            case -1:
                return Result.failed("提交参数不合法");
            case -2:
                return Result.failed("找不到该用户");
            case -3:
                return Result.failed("旧密码错误");
            default:
                return Result.failed();
        }
    }

    @ApiOperation("修改账号状态")
    @PostMapping(value = "/updateStatus/{id}")
    public Result updateStatus(@PathVariable Long id,
                               @RequestParam("status") Integer status) {
        UmsAdmin admin = new UmsAdmin();
        admin.setStatus(status);
        int count = adminService.update(id, admin);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }


    @ApiOperation("给用户分配角色")
    @PostMapping(value = "/role/update")
    public Result updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count >= 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("获取指定用户的角色")
    @GetMapping(value = "/role/{adminId}")
    public Result<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return Result.success(roleList);
    }
}





