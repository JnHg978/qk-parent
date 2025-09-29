package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.entity.Dept;

/**
 * @Author: hjh
 * @Date: 2025/09/29 10:48
 * @Description:
 */
public interface DeptService {
    void addDept(Dept dept);

    PageResult<Dept> page(String name, Integer status, Integer page, Integer pageSize);
}
