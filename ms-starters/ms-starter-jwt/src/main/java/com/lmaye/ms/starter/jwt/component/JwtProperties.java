package com.lmaye.ms.starter.jwt.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * -- JWT Properties
 *
 * @author lmay.Zhou
 * @date 2020/9/27 17:21 星期日
 * @since Email: lmay_zlm@meten.com
 */
@Data
@ConfigurationProperties("jwt")
public class JwtProperties {
    /**
     * 存储
     */
    private String store = "local";

    /**
     * 发行人
     */
    private String issuer = "lmay";

    /**
     * 过期时间
     */
    private int expiresIn = 7200;

    /**
     * 续租，如果续租，每次操作之后重置过期时间，模拟session过期: false:不续租; true：续租;
     */
    private boolean renewal = false;
}
