package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.entity.Course;

/**
 * @Author: hjh
 * @Date: 2025/10/06 16:14
 * @Description:
 */
public interface CourseService {
    PageResult<Course> page(String name, Integer subject, Integer target, Integer page, Integer pageSize);
}
