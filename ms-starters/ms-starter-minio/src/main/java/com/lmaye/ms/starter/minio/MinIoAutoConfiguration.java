package com.lmaye.ms.starter.minio;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
}
