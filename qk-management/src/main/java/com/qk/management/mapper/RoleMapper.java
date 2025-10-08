package com.qk.management.mapper;

import com.qk.entity.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 19:56
 * @Description:
 */
@Mapper
public interface RoleMapper {
    Long count(String name, String label);

    List<Role> select(String name, String label, Integer offset, Integer pageSize);

    void insert(Role role);

    @Select("select id, name, label, remark, create_time, update_time from role")
    List<Role> selectAll();

    @Delete("delete from role where id = #{id}")
    void deleteById(Integer id);

    @Select("select id, name, label, remark, create_time, update_time from role where id = #{id}")
    Role selectById(Integer id);

    void updateById(Role role);
}
