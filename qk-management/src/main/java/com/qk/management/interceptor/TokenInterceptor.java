package com.qk.management.interceptor;

import com.qk.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: hjh
 * @Date: 2025/10/09 16:04
 * @Description:
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("开始拦截请求......");
        if ("/login".equals(request.getRequestURI())){
            log.info("登录操作, 直接放行");
            return true;
        }
        String token = request.getHeader("token");
        if (token == null){
            log.info("解析 token 出错, 拒绝访问, 401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        try {
            // 解析 token
            Claims claims = JwtUtils.parseToken(token);
        } catch (Exception e) {
            // 捕获解析 token 异常，返回 401 错误
            log.info("解析 token 出错, 拒绝访问, 401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        log.info("令牌合法, 放行");
        return true;
    }
}
