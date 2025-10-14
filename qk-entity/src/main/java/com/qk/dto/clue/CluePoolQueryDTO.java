package com.qk.dto.clue;

import lombok.Data;

/**
 * @Author: hjh
 * @Date: 2025/10/14 18:37
 * @Description:
 */
@Data
public class CluePoolQueryDTO {
    private Integer clueId; // 线索id, 主键
    private String phone; // 手机号
    private Integer channel; // 渠道来源，1:线上活动, 2:推广介绍
    private Integer page = 1;
    private Integer pageSize = 10;
}
