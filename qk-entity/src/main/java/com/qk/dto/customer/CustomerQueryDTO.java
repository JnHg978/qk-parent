package com.qk.dto.customer;

import lombok.Data;

/**
 * @Author: hjh
 * @Date: 2025/10/16 11:22
 * @Description:
 */
@Data
public class CustomerQueryDTO {
    private String phone;
    private String name;
    private Integer channel;
    private Integer subject;
    private Integer page = 1;
    private Integer pageSize = 10;
}
