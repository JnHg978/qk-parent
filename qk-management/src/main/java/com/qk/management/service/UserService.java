package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.dto.UserDTO;
import com.qk.entity.User;

/**
 * @Author: hjh
 * @Date: 2025/10/06 20:29
 * @Description:
 */
public interface UserService {
    PageResult<User> page(UserDTO userDTO);

    void addUser(User user);
}
