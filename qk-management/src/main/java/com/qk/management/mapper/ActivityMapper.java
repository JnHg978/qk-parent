package com.qk.management.mapper;

import com.qk.entity.Activity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 18:10
 * @Description:
 */
@Mapper
public interface ActivityMapper {
    Long count(Integer channel, Integer type, Integer status);

    List<Activity> select(Integer channel, Integer type, Integer status, Integer offset, Integer pageSize);

    void insert(Activity activity);

    @Delete("delete from activity where id = #{id}")
    void deleteById(Integer id);

    @Select("select * from activity where id = #{id}")
    Activity selectById(Integer id);

    void updateById(Activity activity);

    @Select("select * from activity where type = #{type}")
    List<Activity> getByType(Integer type);
}
