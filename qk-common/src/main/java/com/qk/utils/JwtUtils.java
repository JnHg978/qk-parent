package com.qk.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * @Author: hjh
 * @Date: 2025/08/07 20:58
 * @Description:
 */
public class JwtUtils {
     private static final String SECRET_KEY = "aXRoZWltYQ==aXRoZWltYQ==aXRoZWlt";
     private static final Integer EXPIRATION_TIME = 12 * 3600 * 1000;

    /**
     * 生成JWT令牌
     *
     * @param claims 自定义信息
     * @return 放回token
     */
    public static String generateToken(Map<String, Object> claims) {
        // 生成JWT的时间
        long expMillis = System.currentTimeMillis() + EXPIRATION_TIME;
        Date exp = new Date(expMillis);

        //生成 HMAC 密钥，根据提供的字节数组长度选择适当的 HMAC 算法，并返回相应的 SecretKey 对象。
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        // 设置jwt的body
        JwtBuilder builder = Jwts.builder()
                // 设置签名使用的签名算法和签名使用的秘钥
                .signWith(key)
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .claims(claims)
                // 设置过期时间
                .expiration(exp);
        return builder.compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param token 令牌
     * @return 返回token中的载荷部分
     */
    public static Claims parseToken(String token) {
        // 生成 HMAC 密钥，根据提供的字节数组长度选择适当的 HMAC 算法，并返回相应的 SecretKey 对象。
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        // 得到DefaultJwtParser
        JwtParser jwtParser = Jwts.parser()
                // 设置签名的秘钥
                .verifyWith(key)
                .build();
        Jws<Claims> jws = jwtParser.parseSignedClaims(token);
        Claims claims = jws.getPayload();
        return claims;
    }
}
