package com.qk.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: hjh
 * @Date: 2025/10/16 15:03
 * @Description:
 */
@Data
@Builder
public class ClueInfoDO {
    private Integer clueTotal; // 总线索数
    private Integer clueWaitAllot; // 待分配线索数量
    private Integer clueWaitFollow; // 待跟进线索数量
    private Integer clueFollowing; // 跟进中线索数量
    private Integer clueFalse; // 伪线索数量
    private Integer clueConvertBusiness; // 转商机线索数量
}
