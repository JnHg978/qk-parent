package com.qk.management.mapper;

import com.qk.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 19:56
 * @Description:
 */
@Mapper
public interface RoleMapper {
    Integer count(String name, String label);

    List<Role> select(String name, String label, Integer offset, Integer pageSize);

    void insert(Role role);
}
