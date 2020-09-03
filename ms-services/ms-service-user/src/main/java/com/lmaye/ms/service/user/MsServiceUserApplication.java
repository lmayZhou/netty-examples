package com.lmaye.ms.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * -- 用户服务 Application
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2020/7/4 11:36 星期六
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MsServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsServiceUserApplication.class, args);
    }
}
