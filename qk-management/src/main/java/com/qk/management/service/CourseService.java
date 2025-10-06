package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.entity.Course;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 16:14
 * @Description:
 */
public interface CourseService {
    PageResult<Course> page(String name, Integer subject, Integer target, Integer page, Integer pageSize);

    void addCourse(Course course);

    void deleteById(Integer id);

    Course getById(Integer id);

    void updateById(Course course);

    List<Course> getAll();
}
