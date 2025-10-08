package com.qk.management.service.impl;

import cn.hutool.core.bean.BeanUtil;
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
        Long total = activityMapper.count(channel, type, status);
        Integer offset = (page - 1) * pageSize;
        List<Activity> result = activityMapper.select(channel, type, status, offset, pageSize);
        return PageResult.<Activity>builder()
                .total(total)
                .rows(result)
                .build();
    }

    @Override
    public void addActivity(Activity activity) {
        boolean hasNull = BeanUtil.hasNullField(activity, "id", "discount", "voucher", "createTime", "updateTime");
        if (hasNull) {
            throw new RuntimeException("参数错误");
        }
        activity.setCreateTime(LocalDateTime.now());
        activity.setUpdateTime(LocalDateTime.now());
        activityMapper.insert(activity);
    }

    @Override
    public void deleteById(Integer id) {
        activityMapper.deleteById(id);
    }

    @Override
    public Activity getById(Integer id) {
        return activityMapper.selectById(id);
    }

    @Override
    public void updateById(Activity activity) {
        boolean hasNull = BeanUtil.hasNullField(activity, "discount", "voucher", "createTime", "updateTime");
        if (hasNull) {
            throw new RuntimeException("参数错误");
        }
        activity.setUpdateTime(LocalDateTime.now());
        activityMapper.updateById(activity);
    }

    @Override
    public List<Activity> getByType(Integer type) {
        return activityMapper.getByType(type);
    }
}
