package com.qk.management.service.impl;

import com.qk.common.PageResult;
import com.qk.entity.Role;
import com.qk.management.mapper.RoleMapper;
import com.qk.management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 19:56
 * @Description:
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageResult<Role> page(String name, String label, Integer page, Integer pageSize) {
        Integer total = roleMapper.count(name, label);
        Integer offset = (page - 1) * pageSize;
        List<Role> roleList = roleMapper.select(name, label, offset, pageSize);
        return PageResult.<Role>builder()
                .total( total)
                .rows(roleList)
                .build();
    }

    @Override
    public void addRole(Role role) {
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insert(role);
    }
}
