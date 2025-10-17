package com.qk.management.aop;

import cn.hutool.json.JSONUtil;
import com.qk.common.Result;
import com.qk.entity.OperateLog;
import com.qk.management.mapper.OperateLogMapper;
import com.qk.util.CurrentUserHoler;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: hjh
 * @Date: 2025/10/17 18:01
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Pointcut("@annotation(com.qk.management.aop.anno.LogAnno)")
    public void logPointCut(){}

    @Around("logPointCut()")
    public Object addOperateLog(ProceedingJoinPoint point) {

        Object result;
        try {
            long startTime = System.currentTimeMillis();
            result = point.proceed();
            long endTime = System.currentTimeMillis();
            try {
                OperateLog operateLog = OperateLog.builder()
                        .operateUserId(CurrentUserHoler.getCurrentUser())
                        .operateTime(LocalDateTime.now())
                        .className(point.getTarget().getClass().getName())
                        .methodName(point.getSignature().getName())
                        .methodParams(JSONUtil.toJsonStr(point.getArgs()))
                        .returnValue(JSONUtil.toJsonStr(result))
                        .costTime(endTime - startTime)
                        .build();
                operateLogMapper.insert(operateLog);
            } catch (Exception e) {
                log.error("添加操作日志失败");
            }
        } catch (Throwable e) {
            result = Result.error("您的操作失败, 请稍后重试!");
        }
        return result;
    }
}
