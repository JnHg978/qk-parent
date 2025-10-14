package com.qk.management.controller;

import com.qk.common.PageResult;
import com.qk.common.Result;
import com.qk.dto.clue.*;
import com.qk.management.service.ClueService;
import com.qk.vo.clue.ClueFollowVO;
import com.qk.vo.clue.CluePoolVO;
import com.qk.vo.clue.ClueVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: hjh
 * @Date: 2025/10/13 20:20
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/clues")
public class ClueController {

    @Autowired
    private ClueService clueService;

    @GetMapping
    public Result page(ClueQueryDTO clueQueryDTO) {
        log.info("分页查询所有线索");
        PageResult<ClueVO> page = clueService.page(clueQueryDTO);
        return Result.success(page);
    }

    @PostMapping
    public Result save(@RequestBody ClueDTO clueDTO) {
        log.info("新增线索: {}", clueDTO);
        clueService.insert(clueDTO);
        return Result.success();
    }

    @PutMapping("/assign/{clueId}/{userId}")
    public Result assign(@PathVariable Integer clueId, @PathVariable Integer userId) {
        log.info("分配线索: {}", clueId);
        clueService.assign(clueId, userId);
        return Result.success();
    }

    @PutMapping("/false/{id}")
    public Result falseClue(@PathVariable Integer id, @RequestBody FalseClueDTO falseClueDTO) {
        log.info("伪线索: {}", id);
        clueService.falseClue(id, falseClueDTO);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("查询线索: {}", id);
        ClueFollowVO result = clueService.getById(id);
        return Result.success(result);
    }

    @PutMapping
    public Result followClue(@RequestBody FollowClueDTO clueDTO) {
        log.info("更新线索: {}", clueDTO);
        clueService.followClue(clueDTO);
        return Result.success();
    }

    @PutMapping("/toBusiness/{id}")
    public Result convertToBusiness(@PathVariable Integer id) {
        log.info("转商机: {}", id);
        clueService.convertToBusiness(id);
        return Result.success();
    }

    @GetMapping("/pool")
    public Result cluePoolPage(CluePoolQueryDTO cluePoolQueryDTO){
        log.info("线索池分页查询: {}", cluePoolQueryDTO);
        PageResult<CluePoolVO> result = clueService.cluePoolPage(cluePoolQueryDTO);
        return Result.success(result);
    }
}
