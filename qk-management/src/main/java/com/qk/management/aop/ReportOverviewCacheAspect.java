package com.qk.management.aop;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.qk.vo.Overview.OverviewVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: hjh
 * @Date: 2025/10/16 15:19
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class ReportOverviewCacheAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String REDIS_KEY_REPORT_OVERVIEW = "qk:report_overview:";

    @Around("execution(* com.qk.management.service.ReportOverviewService.getReportOverview())")
    public Object getReportOverview(ProceedingJoinPoint pjp) throws Throwable {
        // 从redis中获取key为redisKey的值 如果存在则返回 否则执行方法并返回结果
        String redisKey = REDIS_KEY_REPORT_OVERVIEW + "ReportOverview";

        try {
            String jsonStr = redisTemplate.opsForValue().get(redisKey);
            if (!ObjectUtil.isEmpty(jsonStr)){
                return JSONUtil.toBean(jsonStr, OverviewVO.class);
            }
        } catch (Exception e) {
            log.info("获取报表总览缓存失败");
        }

        Object result = pjp.proceed();
        try {
            redisTemplate.opsForValue().set(redisKey, JSONUtil.toJsonStr(result));
        } catch (Exception e) {
            log.error("设置报表总览缓存失败");
        }
        return result;
    }
}
