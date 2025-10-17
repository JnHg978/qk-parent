package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.qk.domain.BizInfoDO;
import com.qk.domain.ClueInfoDO;
import com.qk.management.mapper.BizMapper;
import com.qk.management.mapper.ClueMapper;
import com.qk.management.service.ReportOverviewService;
import com.qk.vo.Overview.OverviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: hjh
 * @Date: 2025/10/16 14:59
 * @Description:
 */
@Service
public class ReportOverviewServiceImpl implements ReportOverviewService {

    @Autowired
    private ClueMapper clueMapper;

    @Autowired
    private BizMapper bizMapper;

    @Override
    public OverviewVO getReportOverview() {
        ClueInfoDO clueInfoDO = clueMapper.selectClueInfo();
        BizInfoDO bizInfoDO = bizMapper.selectBizInfo();
        OverviewVO overviewVO = BeanUtil.copyProperties(clueInfoDO, OverviewVO.class);
        BeanUtil.copyProperties(bizInfoDO, overviewVO);
        return overviewVO;
    }
}
