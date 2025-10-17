package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.business.BizPoolQueryDTO;
import com.qk.dto.business.BizQueryDTO;
import com.qk.dto.business.FollowBizDTO;
import com.qk.entity.Business;
import com.qk.management.aop.anno.LogAnno;
import com.qk.management.service.BizService;
import com.qk.vo.business.BizVO;
import com.qk.vo.business.BizFollowVO;
import com.qk.vo.business.BizPoolVO;
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

    @LogAnno
    @PostMapping
    public Result addBusiness(@RequestBody Business business){
        log.info("保存参数：{}", business);
        bizService.addBusiness(business);
        return Result.success();
    }

    @LogAnno
    @PutMapping("/assign/{businessId}/{userId}")
    public Result assign(@PathVariable Integer businessId, @PathVariable Integer userId){
        log.info("分配参数：businessId={}, userId={}", businessId, userId);
        bizService.assign(businessId, userId);
        return Result.success();
    }

    @LogAnno
    @PutMapping("/back/{id}")
    public Result back(@PathVariable Integer id){
        log.info("退回参数：{}", id);
        bizService.back(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getRecordById(@PathVariable Integer id){
        log.info("查询参数：{}", id);
        BizFollowVO bizFollowVO = bizService.getRecordById(id);
        return Result.success(bizFollowVO);
    }

    @LogAnno
    @PutMapping
    public Result followBusiness(@RequestBody FollowBizDTO followBizDTO){
        log.info("修改参数：{}", followBizDTO);
        bizService.followBusiness(followBizDTO);
        return Result.success();
    }

    @GetMapping("/pool")
    public Result poolPage(BizPoolQueryDTO bizPoolQueryDTO){
        PageResult<BizPoolVO> result = bizService.poolPage(bizPoolQueryDTO);
        return Result.success(result);
    }

    @LogAnno
    @PostMapping("/toCustomer/{id}")
    public Result convertToCustomer(@PathVariable Integer id){
        log.info("转客户参数：{}", id);
        bizService.convertToCustomer(id);
        return Result.success();
    }
}
