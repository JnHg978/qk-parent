package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.entity.Role;

/**
 * @Author: hjh
 * @Date: 2025/10/06 19:55
 * @Description:
 */
public interface RoleService {
    PageResult<Role> page(String name, String label, Integer page, Integer pageSize);
}
