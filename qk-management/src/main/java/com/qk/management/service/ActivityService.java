package com.qk.management.service;

import com.qk.common.PageResult;
import com.qk.vo.ActivityVO;
import com.qk.entity.Activity;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 18:08
 * @Description:
 */
public interface ActivityService {

    PageResult<ActivityVO> page(Integer channel, Integer type, Integer status, Integer page, Integer pageSize);

    void addActivity(Activity activity);

    void deleteById(Integer id);

    Activity getById(Integer id);

    void updateById(Activity activity);

    List<Activity> getByType(Integer type);
}
