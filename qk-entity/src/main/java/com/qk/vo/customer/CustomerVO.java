package com.qk.vo.customer;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: hjh
 * @Date: 2025/10/16 11:24
 * @Description:
 */
@Data
@Builder
public class CustomerVO {
    private Integer id;
    private String phone;
    private Integer channel;
    private String name;
    private Integer gender;
    private Integer age;
    private String wechat;
    private String qq;
    private Integer degree;
    private Integer jobStatus;
    private Integer subject;
    private Integer courseId;
    private Integer businessId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String courseName;
}
