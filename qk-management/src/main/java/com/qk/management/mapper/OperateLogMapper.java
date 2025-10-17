package com.qk.management.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qk.entity.OperateLog;
import com.qk.vo.log.OperateLogVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: hjh
 * @Date: 2025/10/17 17:58
 * @Description:
 */
@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLog> {
    Page<OperateLogVO> page(Page<OperateLogVO> pageParam, String operateUserName);
}
