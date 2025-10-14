package com.qk.management.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.dto.clue.ClueQueryDTO;
import com.qk.entity.Clue;
import com.qk.management.mapper.ClueMapper;
import com.qk.management.service.ClueService;
import com.qk.vo.ClueVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/13 20:19
 * @Description:
 */
@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue> implements ClueService {
    @Override
    public PageResult<ClueVO> page(ClueQueryDTO clueQueryDTO) {
        Long total = this.baseMapper.selectCount(null);
        clueQueryDTO.setPage((clueQueryDTO.getPage() - 1) * clueQueryDTO.getPageSize());
        List<ClueVO> rows = this.baseMapper.selectPage(clueQueryDTO);
        return PageResult.<ClueVO>builder()
                .total(total)
                .rows(rows)
                .build();
    }
}
