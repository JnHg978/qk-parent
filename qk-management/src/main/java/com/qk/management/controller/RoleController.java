package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Role;
import com.qk.management.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: hjh
 * @Date: 2025/10/06 19:54
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public Result page(@RequestParam(required = false) String name,
                       @RequestParam(required = false) String label,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("查询所有角色");
        PageResult<Role> result = roleService.page(name, label, page, pageSize);
        return Result.success(result);
    }

    @PostMapping
    public Result addRole(@RequestBody Role role){
        log.info("添加角色: {}", role);
        roleService.addRole(role);
        return Result.success();
    }
}
