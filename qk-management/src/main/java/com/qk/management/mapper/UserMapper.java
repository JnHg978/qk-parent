package com.qk.management.mapper;

import com.qk.dto.UserDTO;
import com.qk.entity.User;
import org.apache.ibatis.annotations.Delete;
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
}
