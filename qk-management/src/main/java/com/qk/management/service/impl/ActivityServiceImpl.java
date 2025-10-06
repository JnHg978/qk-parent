package com.qk.management.service.impl;

import com.qk.common.PageResult;
import com.qk.entity.Activity;
import com.qk.management.mapper.ActivityMapper;
import com.qk.management.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 18:09
 * @Description:
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;


    @Override
    public PageResult<Activity> page(Integer channel, Integer type, Integer status, Integer page, Integer pageSize) {
        Integer count = activityMapper.count(channel, type, status);
        Integer offset = (page - 1) * pageSize;
        List<Activity> result = activityMapper.select(channel, type, status, offset, pageSize);
        return PageResult.<Activity>builder()
                .total( count)
                .rows(result)
                .build();
    }

    @Override
    public void addActivity(Activity activity) {
        activity.setCreateTime(LocalDateTime.now());
        activity.setUpdateTime(LocalDateTime.now());
        activityMapper.insert(activity);
    }
}
