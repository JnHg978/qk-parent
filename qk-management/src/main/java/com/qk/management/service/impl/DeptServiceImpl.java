package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.qk.common.PageResult;
import com.qk.entity.Dept;
import com.qk.management.mapper.DeptMapper;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/09/29 10:48
 * @Description:
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Dept> getAll() {
        return deptMapper.getAll();
    }

    @Override
    public void updateById(Dept dept) {
        boolean hasNull = BeanUtil.hasNullField(dept, "createTime", "updateTime");
        if (hasNull) {
            throw new RuntimeException("参数错误");
        }
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateById(dept);
    }

    @Override
    public Dept selectById(Integer id) {
        return deptMapper.selectById(id);
    }

    @Override
    public void deleteById(Integer id) {
        if (userMapper.countByDeptId(id) > 0) {
            throw new RuntimeException("该部门下有用户，请先删除用户");
        }else {
            deptMapper.deleteById(id);
        }

    }

    @Override
    public PageResult<Dept> page(String name, Integer status, Integer page, Integer pageSize) {
        Long total = deptMapper.count(name,status);

        Integer offset = (page - 1) * pageSize;

        List<Dept> rows = deptMapper.select(name,status,offset,pageSize);

        return PageResult.<Dept>builder()
                .total(total)
                .rows(rows)
                .build();
    }

    @Override
    public void addDept(Dept dept) {
        boolean hasNull = BeanUtil.hasNullField(dept, "id", "createTime", "updateTime");
        if (hasNull) {
            throw new RuntimeException("参数错误");
        }
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }
}
