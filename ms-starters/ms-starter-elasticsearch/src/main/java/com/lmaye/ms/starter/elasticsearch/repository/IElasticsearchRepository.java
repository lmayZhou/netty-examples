package com.lmaye.ms.starter.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * -- Elasticsearch Repository
 *
 * @author lmay.Zhou
 * @date 2020/9/25 15:09 星期五
 * @since Email: lmay_zlm@meten.com
 */
@NoRepositoryBean
public interface IElasticsearchRepository<T, ID extends Serializable> extends ElasticsearchRepository<T, ID> {
}
