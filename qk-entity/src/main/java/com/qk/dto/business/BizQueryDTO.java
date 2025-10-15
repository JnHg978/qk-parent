package com.qk.dto.business;

import lombok.Data;

/**
 * @Author: hjh
 * @Date: 2025/10/14 20:41
 * @Description:
 */
@Data
public class BizQueryDTO {
    private Integer businessId;
    private String name;
    private String phone;
    private Integer status;
    private String assignName;
    private Integer page = 1;
    private Integer pageSize = 10;
}
