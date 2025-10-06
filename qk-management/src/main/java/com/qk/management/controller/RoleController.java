package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Role;
import com.qk.management.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        log.info("分页查询角色, 参数: name: {}, label: {}, page: {}, pageSize: {}", name, label, page, pageSize);
        PageResult<Role> result = roleService.page(name, label, page, pageSize);
        return Result.success(result);
    }

    @PostMapping
    public Result addRole(@RequestBody Role role){
        log.info("添加角色: {}", role);
        roleService.addRole(role);
        return Result.success();
    }

    @GetMapping("/list")
    public Result getAll(){
        log.info("查询所有角色");
        List<Role> roleList = roleService.getAll();
        return Result.success(roleList);
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("删除角色, id: {}", id);
        roleService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("查询角色, id: {}", id);
        Role role = roleService.selectById(id);
        return Result.success(role);
    }
}
