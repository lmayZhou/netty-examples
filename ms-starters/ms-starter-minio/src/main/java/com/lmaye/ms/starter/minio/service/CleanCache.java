package com.lmaye.ms.starter.minio.service;

/**
 * -- Clean Cache
 *
 * @author lmay.Zhou
 * @date 2020/10/12 18:08 星期一
 * @since Email: lmay_zlm@meten.com
 */
public interface CleanCache {
    /**
     * 清除缓存
     *
     * @param directory     目录
     * @param aliveDuration 存活时间
     */
    void clean(String directory, Long aliveDuration);
}
