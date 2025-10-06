package com.qk.management.mapper;

import com.qk.dto.UserDTO;
import com.qk.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 20:30
 * @Description:
 */
@Mapper
public interface UserMapper {
    Integer count(UserDTO userDTO);

    List<User> select(UserDTO userDTO);

    void insert(User user);

    void deleteById(List<Integer> ids);

    @Select("select * from user where id = #{id}")
    User selectById(Integer id);

    void updateById(User user);

    @Select("select * from user")
    List<User> getAll();

    List<User> selectByRole(String roleLabel);

    List<User> selectByDept(Integer deptId);

    @Select("select count(*) from user where role_id = #{id}")
    Integer countByRoleId(Integer id);
}
