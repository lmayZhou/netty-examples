package com.lmaye.ms.starter.jwt;

import com.lmaye.ms.starter.jwt.component.JwtProperties;
import com.lmaye.ms.starter.jwt.store.LocalTokenStore;
import com.lmaye.ms.starter.jwt.store.RedisTokenStore;
import com.lmaye.ms.starter.jwt.store.TokenStore;
import com.lmaye.ms.starter.jwt.utils.JwtTokenUtils;
import com.lmaye.ms.starter.redis.RedisAutoConfiguration;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

/**
 * -- JWT Auto Configuration
 *
 * @author lmay.Zhou
 * @date 2020/9/27 17:18 星期日
 * @since Email: lmay_zlm@meten.com
 */
@Configurable
@EnableConfigurationProperties(JwtProperties.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class JwtAutoConfiguration {
    private final RedissonClient redissonClient;

    public JwtAutoConfiguration(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * JWT屬性
     *
     * @return JwtProperties
     */
    @Bean
    public JwtProperties jwtProperties() {
        return new JwtProperties();
    }

    /**
     * JWT工具
     *
     * @return JwtTokenUtils
     */
    @Bean
    public JwtTokenUtils jwtTokenUtils() {
        return new JwtTokenUtils(jwtProperties());
    }

    /**
     * 初始化Redis存儲，如果沒有包含redis的jar则使用本地存储
     *
     * @return TokenStore
     */
    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    @ConditionalOnMissingBean(TokenStore.class)
    @ConditionalOnProperty(value = "store", prefix = "jwt", havingValue = "redis")
    public TokenStore redisTokenStore() {
        if (Objects.isNull(redissonClient)) {
            return new LocalTokenStore(jwtProperties());
        } else {
            return new RedisTokenStore(jwtProperties(), redissonClient);
        }
    }

    /**
     * 初始化本地存儲，默認本地存儲
     *
     * @return TokenStore
     */
    @Bean
    @ConditionalOnMissingBean(TokenStore.class)
    @ConditionalOnProperty(value = "store", prefix = "jwt", havingValue = "local", matchIfMissing = true)
    public TokenStore localTokenStore() {
        return new LocalTokenStore(jwtProperties());
    }
}
