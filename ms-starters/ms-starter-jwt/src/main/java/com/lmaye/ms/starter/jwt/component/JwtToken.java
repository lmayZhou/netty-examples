package com.lmaye.ms.starter.jwt.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * -- JWT Token
 *
 * @author lmay.Zhou
 * @date 2020/9/27 17:39 星期日
 * @since Email: lmay_zlm@meten.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class JwtToken<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 授权作用域
     */
    private String scope;

    /**
     * 过期时间，单位（秒）
     */
    private int expiresIn;

    /**
     * 用户
     */
    private T user;
}
