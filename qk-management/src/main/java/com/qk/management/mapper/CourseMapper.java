package com.qk.management.mapper;

import com.qk.entity.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 16:15
 * @Description:
 */
@Mapper
public interface CourseMapper {
    List<Course> select(String name, Integer subject, Integer target, Integer offset, Integer pageSize);

    Integer count(String name, Integer subject, Integer target);

    void insert(Course course);

    @Delete("delete from course where id = #{id}")
    void deleteById(Integer id);

    @Select("select * from course where id = #{id}")
    Course selectById(Integer id);

    void updateById(Course course);

    @Select("select * from course")
    List<Course> selectAll();

    @Select("select * from course where subject = #{subject}")
    List<Course> getBySubject(Integer subject);
}
