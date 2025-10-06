package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.dto.UserDTO;
import com.qk.entity.User;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 20:29
 * @Description:
 */
public interface UserService {
    PageResult<User> page(UserDTO userDTO);

    void addUser(User user);

    void deleteById(List<Integer> id);

    User getById(Integer id);

    void updateById(User user);

    List<User> getAll();

    List<User> getByRole(String roleLabel);

    List<User> getByDept(Integer deptId);

    Integer countByRoleId(Integer id);
}
