package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Activity;
import com.qk.management.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: hjh
 * @Date: 2025/10/06 18:08
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    // 分页
    @GetMapping
    public Result page(@RequestParam(required = false) Integer channel,
                       @RequestParam(required = false) Integer type,
                       @RequestParam(required = false) Integer status,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResult<Activity> result = activityService.page(channel, type, status, page, pageSize);
        return Result.success(result);
    }

    @PostMapping
    public Result addActivity(@RequestBody Activity activity) {
        log.info("添加活动: {}", activity);
        activityService.addActivity(activity);
        return Result.success();
    }
}
