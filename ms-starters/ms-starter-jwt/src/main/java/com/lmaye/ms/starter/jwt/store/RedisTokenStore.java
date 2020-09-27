package com.lmaye.ms.starter.jwt.store;

import com.lmaye.ms.core.utils.IdUtils;
import com.lmaye.ms.starter.jwt.component.JwtProperties;
import com.lmaye.ms.starter.jwt.component.JwtToken;
import com.lmaye.ms.starter.jwt.constant.Scope;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * -- Redis Token Store
 *
 * @author lmay.Zhou
 * @date 2020/9/27 17:58 星期日
 * @since Email: lmay_zlm@meten.com
 */
public class RedisTokenStore implements TokenStore {
    private final JwtProperties jwtProperties;

    private final RedissonClient redissonClient;

    public RedisTokenStore(final JwtProperties jwtProperties, final RedissonClient redissonClient) {
        this.jwtProperties = jwtProperties;
        this.redissonClient = redissonClient;
    }

    @Override
    public <T> JwtToken<T> saveToken(String userId, String scope, T iotUserDetails) {
        Assert.notNull(redissonClient, "Redis config non exist");

        JwtToken<T> jwtToken = new JwtToken<>();
        jwtToken.setId(IdUtils.nextStrId())
                .setUserId(userId)
                .setExpiresIn(jwtProperties.getExpiresIn())
                .setScope(scope)
                .setUser(iotUserDetails);

        redissonClient.getBucket(getKey(jwtToken.getUserId(), jwtToken.getScope()))
                .set(jwtToken, jwtToken.getExpiresIn(), TimeUnit.SECONDS);
        return jwtToken;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> JwtToken<T> getToken(String userId, String scope) {
        Assert.notNull(redissonClient, "Redis config non exist");

        String key = getKey(userId, scope);
        RBucket<JwtToken> bucket = redissonClient.getBucket(key);
        JwtToken<T> jwtToken = bucket.get();

        if (jwtProperties.isRenewal() && !Objects.isNull(jwtToken)) {
            redissonClient.getBucket(getKey(jwtToken.getUserId(), jwtToken.getScope()))
                    .expire(jwtToken.getExpiresIn(), TimeUnit.SECONDS);
        }

        return jwtToken;
    }

    @Override
    public boolean deleteToken(String userId, String scope) {
        Assert.notNull(redissonClient, "Redis config non exist");

        redissonClient.getBucket(getKey(userId, scope)).delete();
        return true;
    }

    @Override
    public boolean deleteToken(String userId) {
        Assert.notNull(redissonClient, "Redis config non exist");

        for (Scope scope : Scope.values()) {
            redissonClient.getBucket(getKey(userId, scope.name())).delete();
        }
        return true;
    }

    @Override
    public String getKey(String userId, String scope) {
        Assert.notNull(jwtProperties, "Jwt config non exist");
        return jwtProperties.getIssuer() + PREFIX + userId + ":" + scope;
    }
}
