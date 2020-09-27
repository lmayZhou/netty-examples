package com.lmaye.ms.starter.jwt.store;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.lmaye.ms.core.utils.IdUtils;
import com.lmaye.ms.starter.jwt.component.JwtProperties;
import com.lmaye.ms.starter.jwt.component.JwtToken;
import com.lmaye.ms.starter.jwt.constant.Scope;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * -- Local Token Store
 *
 * @author lmay.Zhou
 * @date 2020/9/27 17:57 星期日
 * @since Email: lmay_zlm@meten.com
 */
public class LocalTokenStore implements TokenStore, InitializingBean {
    private final JwtProperties jwtProperties;

    private Cache<String, JwtToken> cache;

    public LocalTokenStore(final JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(jwtProperties, "Jwt config non exist");

        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .maximumSize(10000);

        if (jwtProperties.isRenewal()) {
            cacheBuilder.expireAfterAccess(jwtProperties.getExpiresIn(), TimeUnit.SECONDS);
        } else {
            cacheBuilder.expireAfterWrite(jwtProperties.getExpiresIn(), TimeUnit.SECONDS);
        }

        cache = cacheBuilder.build();
    }

    @Override
    public <T> JwtToken<T> saveToken(String userId, String scope, T iotUserDetails) {
        JwtToken<T> jwtToken = new JwtToken<>();
        jwtToken.setId(IdUtils.nextStrId())
                .setUserId(userId)
                .setExpiresIn(jwtProperties.getExpiresIn())
                .setScope(scope)
                .setUser(iotUserDetails);
        cache.put(getKey(jwtToken.getUserId(), jwtToken.getScope()), jwtToken);
        return jwtToken;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> JwtToken<T> getToken(String userId, String scope) {
        return cache.getIfPresent(getKey(userId, scope));
    }

    @Override
    public boolean deleteToken(String userId, String scope) {
        cache.invalidate(getKey(userId, scope));
        return true;
    }

    @Override
    public boolean deleteToken(String userId) {
        for (Scope scope : Scope.values()) {
            cache.invalidate(getKey(userId, scope.name()));
        }
        return true;
    }

    @Override
    public String getKey(String userId, String scope) {
        Assert.notNull(jwtProperties, "Jwt config non exist");
        return jwtProperties.getIssuer() + PREFIX + userId + ":" + scope;
    }
}
