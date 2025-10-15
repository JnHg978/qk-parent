package com.qk.vo.business;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author: hjh
 * @Date: 2025/10/15 20:52
 * @Description:
 */
@Data
@Builder
public class BizFollowVO {
    private Integer id;
    private String name; // 修改为 String 类型
    private String phone;
    private Integer channel;
    private Integer gender;
    private Integer age;
    private String wechat;
    private String qq;
    private Integer subject;
    private Integer courseId;
    private Integer degree;
    private Integer jobStatus;
    private String remark;
    private Integer status;
    private Integer userId;
    private Integer clueId;
    private String nextTime;
    private String createTime;
    private String updateTime;

    private List<BizTrackRecordVO> trackRecords;
}
