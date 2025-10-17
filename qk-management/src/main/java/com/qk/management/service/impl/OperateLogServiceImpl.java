package com.qk.management.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qk.common.PageResult;
import com.qk.entity.OperateLog;
import com.qk.management.mapper.OperateLogMapper;
import com.qk.management.service.OperateLogService;
import com.qk.vo.log.OperateLogVO;
import org.springframework.stereotype.Service;

/**
 * @Author: hjh
 * @Date: 2025/10/17 19:42
 * @Description:
 */
@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements OperateLogService {
    @Override
    public PageResult<OperateLogVO> page(String operateUserName, Integer page, Integer pageSize) {
        Page<OperateLogVO> pageParam = Page.of(page, pageSize);
        Page<OperateLogVO> operateLogPage = baseMapper.page(pageParam, operateUserName);
        return PageResult.<OperateLogVO>builder()
                .total(operateLogPage.getTotal())
                .rows(operateLogPage.getRecords())
                .build();
    }
}
