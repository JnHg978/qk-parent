package com.qk.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 活动实体类
 */
@Data
public class Activity {
    /**
     * 活动ID，主键
     */
    private Integer id;

    /**
     * 渠道来源，1:线上活动, 2:推广介绍
     */
    private Integer channel;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 活动简介
     */
    private String description;

    /**
     * 活动类型，1:课程折扣, 2:代金券
     */
    private Integer type;

    /**
     * 课程折扣
     */
    private Double discount;

    /**
     * 代金券金额（元）
     */
    private Integer voucher;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}