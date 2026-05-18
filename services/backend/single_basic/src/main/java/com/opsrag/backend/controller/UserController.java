package com.opsrag.backend.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opsrag.backend.common.context.BaseContext;
import com.opsrag.backend.common.response.PageDTO;
import com.opsrag.backend.common.response.PageQuery;
import com.opsrag.backend.common.response.Result;
import com.opsrag.backend.common.utils.BeanUtils;
import com.opsrag.backend.pojo.Entity.User;
import com.opsrag.backend.pojo.dto.UserCreateDTO;
import com.opsrag.backend.pojo.dto.UserLoginDTO;
import com.opsrag.backend.pojo.vo.UserLoginVO;
import com.opsrag.backend.pojo.vo.UserVO;
import com.opsrag.backend.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2026-04-12
 */
@Tag(name = "用户接口")
@Slf4j
@RestController
@RequestMapping
public class UserController {
    @Resource
    private IUserService userService;

    @Operation(summary = "登录接口",description = "返回token，后续请求需要在请求头中加入 名为token，值为具体token（如123456798）")
    @PostMapping("/user/login")
    public Result login(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.login(userLoginDTO);
    }

    @Operation(summary = "退出登录",description = "")
    @GetMapping("/user/logout")
    public Result logout() {
        return userService.logout();
    }

    @Operation(summary = "创建用户",description = "只有具有管理员权限的用户才能够创建用户")
    @PostMapping("/user/ops/account")
    public Result createAccount(@RequestBody UserCreateDTO userCreateDTO) {


        return userService.createAccount(userCreateDTO);
    }
    @Operation(summary = "更新用户",description = "只能更新比自己级别低的用户")
    @PutMapping("/user/ops/account")
    public Result updateAccount(@RequestBody User user) {
        return userService.updateAccount(user);
    }
    @Operation(summary = "获取指定userId的用户信息",description = "返回用户信息")
    @GetMapping("/user/ops/account")
    public Result getAccount(@RequestParam Long userId) {
        return userService.getAccount(userId);
    }

    @Operation(summary = "分页查询用户")
    @GetMapping("/user/ops/account/page")
    public Result page(@ParameterObject PageQuery pageQuery){
        Page<User> p =  userService.page(pageQuery.toMpPage("update_time", false));
        Page<UserVO> voPage = (Page<UserVO>) p.convert(user -> {
            UserVO vo = new UserVO();
            BeanUtils.copyProperties(user, vo);
            // 如果需要额外处理，在这里添加
            return vo;
        });
        return Result.success(PageDTO.of(voPage));
    }
    @Operation(summary = "设置密码")
    @PutMapping("/user/ops/account/password")
    public Result resetPassword(Long userId,String password){
        return userService.resetPassword(userId,password);
    }
}
