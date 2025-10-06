package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.UserDTO;
import com.qk.entity.User;
import com.qk.management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: hjh
 * @Date: 2025/10/06 20:29
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Result page(UserDTO userDTO) {
        log.info("分页查询用户, 参数: {}", userDTO);
        PageResult<User> result = userService.page(userDTO);
        return Result.success(result);
    }
}
