package com.qk.management.service.impl;

import com.qk.common.PageResult;
import com.qk.entity.Course;
import com.qk.management.mapper.CourseMapper;
import com.qk.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 16:14
 * @Description:
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public PageResult<Course> page(String name, Integer subject, Integer target, Integer page, Integer pageSize) {
        Integer total = courseMapper.count(name, subject, target);
        Integer offset = (page - 1) * pageSize;
        List<Course> courseList = courseMapper.select(name, subject, target, offset, pageSize);
        return PageResult.<Course>builder()
                .total(total)
                .rows(courseList)
                .build();
    }

    @Override
    public void addCourse(Course course) {
        course.setCreateTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());
        courseMapper.insert(course);
    }

    @Override
    public void deleteById(Integer id) {
        courseMapper.deleteById(id);
    }

    @Override
    public Course getById(Integer id) {
        return courseMapper.selectById(id);
    }
}
