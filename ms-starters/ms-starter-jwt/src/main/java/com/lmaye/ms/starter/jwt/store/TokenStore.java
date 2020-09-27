package com.lmaye.ms.starter.jwt.store;

import com.lmaye.ms.starter.jwt.component.JwtToken;

/**
 * -- Token Store
 *
 * @author lmay.Zhou
 * @date 2020/9/27 17:53 星期日
 * @since Email: lmay_zlm@meten.com
 */
public interface TokenStore {
    /**
     * 缓存前缀
     */
    String PREFIX = ":Authorization:";

    /**
     * 保存凭证
     *
     * @param userId         用户编号
     * @param scope          作用域
     * @param iotUserDetails 泛型
     * @return boolean
     */
    <T> JwtToken<T> saveToken(String userId, String scope, T iotUserDetails);

    /**
     * 获取凭证
     *
     * @param userId 用户编号
     * @param scope  作用域
     * @return JwtToken
     */
    <T> JwtToken<T> getToken(String userId, String scope);

    /**
     * 删除凭证
     *
     * @param userId 用户编号
     * @param scope  作用域
     * @return boolean
     */
    boolean deleteToken(String userId, String scope);

    /**
     * 删除凭证
     *
     * @param userId 用户编号
     * @return boolean
     */
    boolean deleteToken(String userId);

    /**
     * 获取键
     *
     * @param userId 用户编号
     * @param scope  作用域
     * @return String
     */
    String getKey(String userId, String scope);
}
