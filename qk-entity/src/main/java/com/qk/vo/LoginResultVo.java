package com.qk.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录结果实体类
 * 用于封装登录后返回给前端的结果
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResultVo {
    /**
     * 用户ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像URL
     */
    private String image;

    /**
     * 角色标签
     */
    private String roleLabel;

    /**
     * 访问令牌
     */
    private String token;
}