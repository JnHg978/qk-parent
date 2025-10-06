package com.qk.management.mapper;

import com.qk.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/06 18:10
 * @Description:
 */
@Mapper
public interface ActivityMapper {
    Integer count(Integer channel, Integer type, Integer status);

    List<Activity> select(Integer channel, Integer type, Integer status, Integer offset, Integer pageSize);

    void insert(Activity activity);
}
