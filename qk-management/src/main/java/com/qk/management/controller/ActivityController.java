package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Activity;
import com.qk.management.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") Integer id) {
        log.info("删除部门的id: {}", id);
        activityService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        log.info("查询部门的id: {}", id);
        Activity activity = activityService.getById(id);
        return Result.success(activity);
    }

    @PutMapping
    public Result updateById(@RequestBody Activity activity) {
        log.info("修改部门: {}", activity);
        activityService.updateById(activity);
        return Result.success();
    }

    @GetMapping("/type/{type}")
    public Result getByType(@PathVariable("type") Integer type) {
        log.info("查询部门的type: {}", type);
        List<Activity> activities = activityService.getByType(type);
        return Result.success(activities);
    }
}
