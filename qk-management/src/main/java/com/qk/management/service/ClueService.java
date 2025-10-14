package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.clue.ClueDTO;
import com.qk.dto.clue.ClueQueryDTO;
import com.qk.dto.clue.FalseClueDTO;
import com.qk.entity.Clue;
import com.qk.vo.clue.ClueFollowVO;
import com.qk.vo.clue.ClueVO;

/**
 * @Author: hjh
 * @Date: 2025/10/13 20:19
 * @Description:
 */
public interface ClueService extends IService<Clue> {
    PageResult<ClueVO> page(ClueQueryDTO clueQueryDTO);

    void insert(ClueDTO clueDTO);

    void assign(Integer clueId, Integer userId);

    void falseClue(Integer id, FalseClueDTO falseClueDTO);

    ClueFollowVO getById(Integer id);
}
