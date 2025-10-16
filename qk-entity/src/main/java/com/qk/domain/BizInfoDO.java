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
public class BizInfoDO {
    private Integer businessTotal; // 总商机数
    private Integer businessWaitAllot; // 待分配商机数量
    private Integer businessWaitFollow; // 待跟进商机数量
    private Integer businessFollowing; // 跟进中商机数量
    private Integer businessFalse; // 回收商机数量
    private Integer businessConvertCustomer; // 转客户商机数量
}
