package com.lmaye.ms.core.context;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * -- 基础信息
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/1 8:06 星期三
 */
@Getter
@Setter
@Builder
public class BaseInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String userName;

    /**
     * Token
     */
    private String token;

    /**
     * Token作用域
     */
    private String tokenScope;

    /**
     * 用户
     */
    private Object user;

    /**
     * 日志
     */
    private Object log;
}
