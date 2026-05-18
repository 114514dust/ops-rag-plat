package com.opsrag.backend.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opsrag.backend.common.response.PageDTO;
import com.opsrag.backend.common.response.PageQuery;
import com.opsrag.backend.common.response.Result;
import com.opsrag.backend.pojo.Entity.OpsLog;
import com.opsrag.backend.service.impl.OpsLogServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2026-04-12
 */
@Tag(name = "日志接口")
@RestController
@RequestMapping("/log")
public class OpsLogController {
    @Resource
    private OpsLogServiceImpl opsLogService;
    @GetMapping
    @Operation(summary = "分页查询日志")
    public Result page(PageQuery pageQuery){
        Page<OpsLog>res = opsLogService.page(pageQuery.toMpPage("update_time", false));
        return Result.success(PageDTO.of(res));
    }

}
