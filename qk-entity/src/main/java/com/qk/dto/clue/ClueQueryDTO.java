package com.qk.dto.clue;

import com.qk.entity.Clue;
import lombok.Data;

/**
 * @Author: hjh
 * @Date: 2025/10/13 20:23
 * @Description:
 */
@Data
public class ClueQueryDTO {
    private Integer clueId; // 线索id, 主键
    private String phone; // 手机号
    private Integer channel; // 渠道来源，1:线上活动, 2:推广介绍
    private Integer assignName; // 归属人id，关联用户id
    private Integer status; // 线索状态，1:待分配, 2:跟进中, 3:伪线索, 4:转为商机
    private Integer page = 1;
    private Integer pageSize = 10;
}
