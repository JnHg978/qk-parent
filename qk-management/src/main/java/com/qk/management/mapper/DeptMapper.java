package com.qk.management.mapper;

import com.qk.entity.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/09/29 10:47
 * @Description:
 */
@Mapper
public interface DeptMapper {

    @Select("select * from dept where id = #{id}")
    Dept selectById(Integer id);

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    List<Dept> select(@Param("name") String name,
                      @Param("status") Integer status,
                      @Param("offset") Integer offset,
                      @Param("pageSize") Integer pageSize);

    Integer count(String name, Integer status);

    @Insert("insert into dept(name,status,create_time,update_time) values(#{name},#{status},#{createTime},#{updateTime})")
    void insert(Dept dept);

}
