package com.qk.dto.clue;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: hjh
 * @Date: 2025/10/14 10:22
 * @Description:
 */
@Data
@Builder
public class FalseClueDTO {
    private Integer reason; // 伪线索原因 false_reason
    private String remark;  // 伪线索备注 record
}
