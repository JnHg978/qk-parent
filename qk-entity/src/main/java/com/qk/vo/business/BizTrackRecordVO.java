package com.qk.vo.business;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: hjh
 * @Date: 2025/10/15 20:53
 * @Description:
 */
@Data
@Builder
public class BizTrackRecordVO {
    private Integer id;
    private Integer businessId;
    private Integer userId;
    private Integer trackStatus;
    private String keyItems;
    private String nextTime;
    private String record;
    private String createTime;
    private String assignName;
}
