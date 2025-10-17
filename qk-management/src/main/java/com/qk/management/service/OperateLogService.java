package com.qk.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qk.common.PageResult;
import com.qk.entity.OperateLog;
import com.qk.vo.log.OperateLogVO;

/**
 * @Author: hjh
 * @Date: 2025/10/17 19:42
 * @Description:
 */
public interface OperateLogService extends IService<OperateLog> {
    PageResult<OperateLogVO> page(String operateUserName, Integer page, Integer pageSize);
}
