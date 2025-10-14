package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.dto.clue.ClueDTO;
import com.qk.dto.clue.ClueQueryDTO;
import com.qk.entity.Clue;
import com.qk.vo.ClueVO;

/**
 * @Author: hjh
 * @Date: 2025/10/13 20:19
 * @Description:
 */
public interface ClueService extends IService<Clue> {
    PageResult<ClueVO> page(ClueQueryDTO clueQueryDTO);

    void insert(ClueDTO clueDTO);
}
