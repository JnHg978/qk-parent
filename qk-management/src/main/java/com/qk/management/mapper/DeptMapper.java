package com.qk.management.mapper;

import com.qk.entity.Dept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: hjh
 * @Date: 2025/09/29 10:47
 * @Description:
 */
@Mapper
public interface DeptMapper {

    @Insert("insert into dept(name,status,create_time,update_time) values(#{name},#{status},#{createTime},#{updateTime})")
    void insert(Dept dept);
}
