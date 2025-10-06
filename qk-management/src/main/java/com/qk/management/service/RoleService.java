package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.entity.Role;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 19:55
 * @Description:
 */
public interface RoleService {
    PageResult<Role> page(String name, String label, Integer page, Integer pageSize);

    void addRole(Role role);

    List<Role> getAll();

    void deleteById(Integer id);

    Role selectById(Integer id);
}
