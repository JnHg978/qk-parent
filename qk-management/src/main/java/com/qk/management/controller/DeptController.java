package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.entity.Dept;
import com.qk.management.aop.anno.LogAnno;
import com.qk.management.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/09/29 10:49
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/list")
    public Result getAll(){
        log.info("查询所有部门");
        List<Dept> deptList = deptService.getAll();
        return Result.success(deptList);
    }

    @LogAnno
    @PutMapping
    public Result updateById(@RequestBody Dept dept){
        log.info("更新部门: {}", dept);
        deptService.updateById(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable("id") Integer id){
        log.info("查询部门的id: {}", id);
        return Result.success(deptService.selectById(id));
    }

    @LogAnno
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable("id") Integer id){
        log.info("删除部门的id: {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result page(@RequestParam(name = "name", required = false) String name,
                       @RequestParam(name = "status",required = false) Integer status,
                       @RequestParam(name = "page", defaultValue = "1") Integer page,
                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        log.info("分页查询部门, 参数: name: {}, status: {}, page: {}, pageSize: {}", name, status, page, pageSize);
        PageResult<Dept> result = deptService.page(name, status, page, pageSize);
        return Result.success(result);
    }

    @LogAnno
    @PostMapping
    public Result addDept(@RequestBody Dept dept) {
        log.info("添加部门: {}", dept);
        deptService.addDept(dept);
        return Result.success();
    }
}
