package com.lmaye.ms.core.context;

import java.util.Objects;

/**
 * -- Web Application 上下文
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/1 8:07 星期三
 */
public class BaseContext {
    private static final ThreadLocal<BaseInfo> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取请求信息
     *
     * @return BaseInfo
     */
    public static BaseInfo getBaseInfo() {
        BaseInfo baseInfo = THREAD_LOCAL.get();
        if(Objects.isNull(baseInfo)) {
            baseInfo = BaseInfo.builder().build();
            setBaseInfo(baseInfo);
        }
        return baseInfo;
    }

    /**
     * 设置请求信息
     *
     * @param baseInfo 请求信息
     */
    public static void setBaseInfo(BaseInfo baseInfo) {
        THREAD_LOCAL.set(baseInfo);
    }

    /**
     * 清除请求信息
     */
    public static void clear() {
        THREAD_LOCAL.remove();
    }
}
