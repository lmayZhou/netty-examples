package com.lmaye.ms.starter.elasticsearch;

import com.lmaye.ms.starter.elasticsearch.repository.IElasticsearchRepository;
import org.springframework.boot.actuate.autoconfigure.metrics.export.elastic.ElasticProperties;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * -- Elasticsearch Auto Configuration
 *
 * @author lmay.Zhou
 * @date 2020/9/25 14:55 星期五
 * @since Email: lmay_zlm@meten.com
 */
@Configuration
@ConditionalOnBean(ElasticProperties.class)
@AutoConfigureAfter(org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchAutoConfiguration.class)
@EnableElasticsearchRepositories(basePackages = "com.lmaye.ms", basePackageClasses = IElasticsearchRepository.class)
public class ElasticsearchAutoConfiguration {
}
