package com.qk.dto;

import lombok.Data;

/**
 * @Author: hjh
 * @Date: 2025/10/13 20:07
 * @Description:
 */
@Data
public class ClueDTO {
    private String phone; // 手机号
    private Integer channel; // 渠道来源，1:线上活动, 2:推广介绍
    private Integer activityId; // 活动信息，关联活动的id
    private String name; // 客户姓名
    private Integer gender; // 性别，1:男, 2:女
    private Integer age; // 年龄
    private String wechat; // 微信号
    private String qq; // qq号
}
