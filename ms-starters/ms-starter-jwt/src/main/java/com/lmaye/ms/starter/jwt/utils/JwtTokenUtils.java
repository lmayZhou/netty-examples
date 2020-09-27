package com.lmaye.ms.starter.jwt.utils;

import com.google.common.collect.Maps;
import com.lmaye.ms.core.context.ResultCode;
import com.lmaye.ms.core.exception.AuthException;
import com.lmaye.ms.starter.jwt.component.AccessToken;
import com.lmaye.ms.starter.jwt.component.JwtProperties;
import com.lmaye.ms.starter.jwt.component.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * -- JWT Token Utils
 *
 * @author lmay.Zhou
 * @date 2020/9/27 17:34 星期日
 * @since Email: lmay_zlm@meten.com
 */
public class JwtTokenUtils {
    private final JwtProperties jwtProperties;

    /**
     * 密钥
     */
    private String secret = "WkpVU0kwSlM4RDdTMjI4QQ==";

    public JwtTokenUtils(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 生成凭证
     *
     * @param jwtToken JwtToken
     * @return AccessToken
     */
    public AccessToken generate(JwtToken jwtToken) {
        String compact = Jwts.builder()
                .setClaims(Maps.newHashMap())
                .setId(jwtToken.getId())
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(new Date())
                .setSubject(jwtToken.getScope())
                .setAudience(jwtToken.getUserId())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return new AccessToken().setAccessToken(compact).setExpiresIn(jwtToken.getExpiresIn());
    }

    /**
     * 解析
     *
     * @param token 凭证
     * @return Claims
     */
    public Claims parse(String token) throws AuthException {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new AuthException(ResultCode.UNAUTHORIZED, e);
        }
    }

    /**
     * 获取JWT Subject
     *
     * @param claims Claims
     * @return String
     */
    public String getClaimsSubject(Claims claims) {
        return claims.getSubject();
    }

    /**
     * 获取JWT Audience
     *
     * @param claims Claims
     * @return String
     */
    public String getClaimsAudience(Claims claims) {
        return claims.getAudience();
    }
}
