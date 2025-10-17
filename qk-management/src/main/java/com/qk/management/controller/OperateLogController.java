package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.management.service.OperateLogService;
import com.qk.vo.log.OperateLogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hjh
 * @Date: 2025/10/17 19:40
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/logs")
public class OperateLogController {

    @Autowired
    private OperateLogService operateLogService;

    @GetMapping
    public Result page(String operateUserName, Integer page, Integer pageSize) {
        log.info("查询参数：{}, {}, {}", operateUserName, page, pageSize);
        PageResult<OperateLogVO> result = operateLogService.page(operateUserName, page, pageSize);
        return Result.success(result);
    }
}
