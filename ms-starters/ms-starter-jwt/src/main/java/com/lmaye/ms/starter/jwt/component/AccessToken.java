package com.lmaye.ms.starter.jwt.component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * -- Access Token
 *
 * @author lmay.Zhou
 * @date 2020/9/27 17:38 星期日
 * @since Email: lmay_zlm@meten.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class AccessToken implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 授权凭证
     */
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * 过期时间，单位（秒）
     */
    @JsonProperty("expires_in")
    private int expiresIn;
}
