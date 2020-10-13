package com.lmaye.ms.starter.minio;

import com.lmaye.ms.starter.minio.service.ICleanCacheService;
import com.lmaye.ms.starter.minio.service.IMinIoClientService;
import com.lmaye.ms.starter.minio.service.IMinIoFileStoreService;
import com.lmaye.ms.starter.minio.service.impl.CleanCacheServiceImpl;
import com.lmaye.ms.starter.minio.service.impl.MinIoClientServiceImpl;
import com.lmaye.ms.starter.minio.service.impl.MinIoFileStoreServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@ConditionalOnProperty(prefix = "minio.store", value = "enabled", havingValue = "true")
@EnableConfigurationProperties(MinIoStoreProperties.class)
public class MinIoAutoConfiguration {
    @Bean
    IMinIoFileStoreService minIoFileStore() {
        return new MinIoFileStoreServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(ICleanCacheService.class)
    ICleanCacheService cleanCache() {
        return new CleanCacheServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean(IMinIoClientService.class)
    IMinIoClientService minIoClient() {
        return new MinIoClientServiceImpl();
    }
}
