package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.entity.Dept;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/09/29 10:48
 * @Description:
 */
public interface DeptService {
    List<Dept> getAll();

    void updateById(Dept dept);

    Dept selectById(Integer id);

    void deleteById(Integer id);

    void addDept(Dept dept);

    PageResult<Dept> page(String name, Integer status, Integer page, Integer pageSize);

}
