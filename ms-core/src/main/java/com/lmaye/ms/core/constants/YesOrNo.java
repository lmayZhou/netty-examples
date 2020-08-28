package com.lmaye.ms.core.constants;

import lombok.Getter;

import java.util.Objects;

/**
 * -- 是/否枚举
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @date 2020年6月30日 23:37:20
 * @email lmay@lmaye.com
 */
@Getter
public enum YesOrNo {
    /**
     * 枚举值
     */
    NO(0, "否"),
    YES(1, "是");

    /**
     * 枚举编码
     */
    private final Integer code;

    /**
     * 枚举描述
     */
    private final String desc;

    YesOrNo(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 获取枚举对象
     *
     * @param code 枚举编码
     * @return 枚举对象
     */
    public static YesOrNo valueOf(Integer code) {
        Objects.requireNonNull(code, "The matching value cannot be empty");
        for (YesOrNo obj : values()) {
            if (code.equals(obj.getCode())) {
                return obj;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }
}
