package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.dto.user.LoginDTO;
import com.qk.dto.user.UserDTO;
import com.qk.entity.User;
import com.qk.vo.user.LoginResultVo;
import com.qk.vo.user.UserVO;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 20:29
 * @Description:
 */
public interface UserService {
    PageResult<UserVO> page(UserDTO userDTO);

    void addUser(User user);

    void deleteById(List<Integer> id);

    User getById(Integer id);

    void updateById(User user);

    List<User> getAll();

    List<User> getByRole(String roleLabel);

    List<User> getByDept(Integer deptId);

    LoginResultVo login(LoginDTO loginDTO);

    List<String> getAllImage();
}
