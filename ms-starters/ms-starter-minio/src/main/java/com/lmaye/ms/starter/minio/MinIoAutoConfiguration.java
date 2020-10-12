package com.lmaye.ms.starter.minio;

import com.lmaye.ms.starter.minio.service.ICleanCache;
import com.lmaye.ms.starter.minio.service.IMinIoClient;
import com.lmaye.ms.starter.minio.service.IMinIoFileStore;
import com.lmaye.ms.starter.minio.service.impl.CleanCacheImpl;
import com.lmaye.ms.starter.minio.service.impl.MinIoClientImpl;
import com.lmaye.ms.starter.minio.service.impl.MinIoFileStoreImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * -- MinIO Auto Configuration
 *
 * @author lmay.Zhou
 * @date 2020/10/12 15:50 星期一
 * @since Email: lmay_zlm@meten.com
 */
@Configuration
@Profile({"dev", "test"})
@EnableConfigurationProperties(MinIoStoreProperties.class)
public class MinIoAutoConfiguration {
    @Bean
    IMinIoFileStore minIoFileStore() {
        return new MinIoFileStoreImpl();
    }

    @Bean
    @ConditionalOnMissingBean(ICleanCache.class)
    ICleanCache cleanCache() {
        return new CleanCacheImpl();
    }

    @Bean
    @ConditionalOnMissingBean(IMinIoClient.class)
    IMinIoClient minIoClient() {
        return new MinIoClientImpl();
    }
}
