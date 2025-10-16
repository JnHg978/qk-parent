package com.qk.management.controller;

import com.qk.common.Result;
import com.qk.management.service.ReportOverviewService;
import com.qk.vo.Overview.OverviewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hjh
 * @Date: 2025/10/16 14:45
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/report/overview")
public class ReportOverviewController {

    @Autowired
    private ReportOverviewService reportOverviewService;

    @GetMapping
    public Result getInfo(){
        log.info("报表总览");
        OverviewVO result = reportOverviewService.getInfo();
        return Result.success(result);
    }
}
