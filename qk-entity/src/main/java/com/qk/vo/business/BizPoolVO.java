package com.qk.vo.business;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: hjh
 * @Date: 2025/10/16 10:15
 * @Description:
 */
@Data
@Builder
public class BizPoolVO {
    private Integer id;
    private String name; // 修改为 String 类型
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
    private String nextTime;
    private String createTime;
    private String updateTime;
}
