package com.qk.management.filter;

import cn.hutool.core.util.ObjectUtil;
import com.qk.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Author: hjh
 * @Date: 2025/10/09 14:36
 * @Description:
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    private static final String LOGIN_URI = "/login";
    private static final String TOKEN_HEADER_NAME = "token";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("开始过滤请求......");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if (ObjectUtil.equals(httpServletRequest.getRequestURI(), LOGIN_URI)) {
            log.info("登录操作, 直接放行");
            chain.doFilter(request, response);
            return;
        }

        String token = httpServletRequest.getHeader(TOKEN_HEADER_NAME);
        if (token == null) {
            log.info("解析 token 出错, 拒绝访问, 401");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        try {
            // 解析 token
            Claims claims = JwtUtils.parseToken(token);
        } catch (Exception e) {
            // 捕获解析 token 异常，返回 401 错误
            log.info("解析 token 出错, 拒绝访问, 401");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 继续执行后续过滤器或请求处理
        log.info("token 正确, 继续执行后续过滤器或请求处理");
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

}
