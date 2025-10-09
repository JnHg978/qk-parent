package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.dto.LoginDTO;
import com.qk.management.service.UserService;
import com.qk.vo.LoginResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hjh
 * @Date: 2025/10/08 11:21
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Result login(@RequestBody LoginDTO loginDTO) {
        log.info("登录开始...");
        LoginResultVo result = userService.login(loginDTO);
        if (result != null){
            return Result.success(result);
        }else {
            return Result.error("账号/密码错误！");
        }
    }

}
