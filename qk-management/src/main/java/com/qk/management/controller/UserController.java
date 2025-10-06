package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.UserDTO;
import com.qk.entity.User;
import com.qk.management.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public Result addUser(@RequestBody User user) {
        log.info("添加用户: {}", user);
        userService.addUser(user);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable("ids") List<Integer> ids) {
        log.info("删除用户的id: {}", ids);
        userService.deleteById(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        log.info("查询用户的id: {}", id);
        User user = userService.getById(id);
        return Result.success(user);
    }

    @PutMapping
    public Result updateById(@RequestBody User user) {
        log.info("更新用户: {}", user);
        userService.updateById(user);
        return Result.success();
    }
}
