package com.qk.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/09/29 17:45
 * @Description:
 */
@Data
@Builder
public class PageResult<T> {
    private Integer total;
    private List<T> rows;
}
