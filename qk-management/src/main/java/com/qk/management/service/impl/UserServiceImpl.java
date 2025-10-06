package com.qk.management.service.impl;

import com.qk.common.PageResult;
import com.qk.dto.UserDTO;
import com.qk.entity.User;
import com.qk.management.mapper.UserMapper;
import com.qk.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 20:30
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<User> page(UserDTO userDTO) {
        Integer total = userMapper.count(userDTO);
        Integer offset = (userDTO.getPage() - 1) * userDTO.getPageSize();
        userDTO.setPage(offset);
        List<User> userList = userMapper.select(userDTO);
        return PageResult.<User>builder()
                .total(total)
                .rows(userList)
                .build();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()+"123").getBytes()));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }
}
