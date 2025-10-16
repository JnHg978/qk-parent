package com.qk.dto.business;

import lombok.Data;

/**
 * @Author: hjh
 * @Date: 2025/10/16 10:11
 * @Description:
 */
@Data
public class BizPoolQueryDTO {
    private Integer businessId;
    private String phone;
    private String name;
    private Integer subject;
    private Integer page = 1;
    private Integer pageSize = 10;
}
