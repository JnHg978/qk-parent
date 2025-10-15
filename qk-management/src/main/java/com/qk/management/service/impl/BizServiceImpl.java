package com.qk.management.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.dto.business.BizQueryDTO;
import com.qk.entity.Business;
import com.qk.management.mapper.BizMapper;
import com.qk.management.service.BizService;
import com.qk.vo.BizVO;
import org.springframework.stereotype.Service;

/**
 * @Author: hjh
 * @Date: 2025/10/14 20:29
 * @Description:
 */
@Service
public class BizServiceImpl extends ServiceImpl<BizMapper, Business> implements BizService {
    @Override
    public PageResult<BizVO> page(BizQueryDTO bizQueryDTO) {
        Page<BizVO> page = Page.of(bizQueryDTO.getPage(), bizQueryDTO.getPageSize());
        Page<BizVO> result = baseMapper.selectPage(page, bizQueryDTO);
        return PageResult.<BizVO>builder()
                .total(result.getTotal())
                .rows(result.getRecords())
                .build();
    }
}
