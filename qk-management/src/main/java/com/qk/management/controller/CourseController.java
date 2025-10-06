package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Course;
import com.qk.management.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hjh
 * @Date: 2025/10/06 16:13
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public Result page(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Integer subject,
                       @RequestParam(required = false) Integer target,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询课程, 参数: name: {}, subject: {}, target: {}, page: {}, pageSize: {}", name, subject, target, page, pageSize);
        PageResult<Course> result = courseService.page(name, subject, target, page, pageSize);
        return Result.success(result);
    }
}
