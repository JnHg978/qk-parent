package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.business.BizQueryDTO;
import com.qk.entity.Business;
import com.qk.management.service.BizService;
import com.qk.vo.BizVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: hjh
 * @Date: 2025/10/14 20:27
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/businesses")
public class BizController {

    @Autowired
    private BizService bizService;

    @GetMapping
    public Result page(BizQueryDTO bizQueryDTO){
        log.info("分页查询参数：{}", bizQueryDTO);
        PageResult<BizVO> result = bizService.page(bizQueryDTO);
        return Result.success(result);
    }

    @PostMapping
    public Result addBusiness(@RequestBody Business business){
        log.info("保存参数：{}", business);
        bizService.addBusiness(business);
        return Result.success();
    }

    @PutMapping("/assign/{businessId}/{userId}")
    public Result assign(@PathVariable Integer businessId, @PathVariable Integer userId){
        log.info("分配参数：businessId={}, userId={}", businessId, userId);
        bizService.assign(businessId, userId);
        return Result.success();
    }
}
