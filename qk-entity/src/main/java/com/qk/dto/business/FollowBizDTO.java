package com.qk.dto.business;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: hjh
 * @Date: 2025/10/15 21:17
 * @Description:
 */
@Data
public class FollowBizDTO {
    private Integer id;
    private String name;
    private String phone;
    private Integer gender;
    private Integer age;
    private String wechat;
    private String qq;
    private Integer subject;
    private Integer courseId;
    private Integer degree;
    private Integer jobStatus;
    private Integer channel;
    private String remark;
    private Integer status;
    private Integer userId;
    private Integer clueId;
    private LocalDateTime nextTime;

    private String[] keyItems;
    private Integer trackStatus;
    private String record;
}
