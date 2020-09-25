package com.lmaye.ms.starter.elasticsearch.service.impl;

import com.google.common.collect.Lists;
import com.lmaye.ms.core.constants.YesOrNo;
import com.lmaye.ms.core.context.PageResult;
import com.lmaye.ms.core.context.ResultCode;
import com.lmaye.ms.core.exception.ServiceException;
import com.lmaye.ms.core.query.ListQuery;
import com.lmaye.ms.core.query.PageQuery;
import com.lmaye.ms.core.query.Query;
import com.lmaye.ms.core.service.IMsService;
import com.lmaye.ms.starter.elasticsearch.repository.IElasticsearchRepository;
import com.lmaye.ms.starter.elasticsearch.utils.ElasticsearchUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * -- Elasticsearch Service 实现类
 *
 * @author lmay.Zhou
 * @date 2020/9/25 16:33 星期五
 * @since Email: lmay_zlm@meten.com
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class ElasticsearchServiceImpl<R extends IElasticsearchRepository<T, ID>, T, ID extends Serializable>
        implements IMsService<T, ID> {
    /**
     * R
     */
    @Autowired
    private R r;

    /**
     * ElasticsearchOperations
     */
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public <S extends T> S insertOrUpdate(S entity) throws ServiceException {
        try {
            return r.save(entity);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.OPERATION_FAILED, e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) throws ServiceException {
        try {
            return r.saveAll(entities);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.OPERATION_FAILED, e);
        }
    }

    @Override
    public void deleteById(ID id) throws ServiceException {
        try {
            r.deleteById(id);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.OPERATION_FAILED, e);
        }
    }

    @Override
    public Optional<T> findById(ID id) throws ServiceException {
        try {
            return r.findById(id);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.OPERATION_FAILED, e);
        }
    }

    @Override
    public List<T> findAll(Query query) throws ServiceException {
        try {
            Iterable<T> search;
            if (Objects.isNull(query)) {
                search = r.search(QueryBuilders.boolQuery());
            } else {
                QueryBuilder queryBuilder = ElasticsearchUtils.convert(query);
                search = r.search(queryBuilder);
            }
            return Lists.newArrayList(search);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.OPERATION_FAILED, e);
        }
    }

    @Override
    public List<T> findAll(ListQuery query) throws ServiceException {
        try {
            Iterable<T> search;
            if (Objects.isNull(query) || Objects.isNull(query.getQuery())) {
                search = r.search(QueryBuilders.boolQuery());
            } else {
                QueryBuilder queryBuilder = ElasticsearchUtils.convert(query.getQuery());
                search = r.search(queryBuilder);
            }
            return Lists.newArrayList(search);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.OPERATION_FAILED, e);
        }
    }

    @Override
    public PageResult<T> findAll(PageQuery query) throws ServiceException {
        try {
            QueryBuilder queryBuilder = QueryBuilders.boolQuery();
            if (!Objects.isNull(query.getQuery())) {
                queryBuilder = ElasticsearchUtils.convert(query.getQuery());
            }
            Sort sort = Sort.unsorted();
            if (!Objects.isNull(query.getSort())
                    && !CollectionUtils.isEmpty(query.getSort().getOrder())) {
                List<org.springframework.data.domain.Sort.Order> orders = Lists.newLinkedList();
                query.getSort().getOrder().forEach(o ->
                        orders.add(Objects.equals(YesOrNo.YES.getCode(), o.getAsc()) ?
                                Sort.Order.asc(o.getName()) :
                                Sort.Order.desc(o.getName())));
                sort = Sort.by(orders);
            }
            PageRequest pageRequest = PageRequest.of(query.getPageIndex().intValue(),
                    query.getPageSize().intValue(), sort);
            Page<T> page = r.search(queryBuilder, pageRequest);
            return new PageResult<T>()
                    .setPageIndex((long) page.getNumber()).setPageSize((long) page.getSize())
                    .setPages((long) page.getTotalPages())
                    .setTotal(page.getTotalElements()).setRecords(page.getContent());
        } catch (Exception e) {
            throw new ServiceException(ResultCode.OPERATION_FAILED, e);
        }
    }

    @Override
    public long count(Query query) throws ServiceException {
        try {
            Class<T> entityClass = r.getEntityClass();
            Document document = entityClass.getAnnotation(Document.class);
            if (Objects.isNull(document)) {
                return 0L;
            }
            QueryBuilder queryBuilder = ElasticsearchUtils.convert(query);
            SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withIndices(document.indexName())
                    .withTypes(document.type()).withQuery(queryBuilder).build();
            return elasticsearchOperations.count(searchQuery);
        } catch (Exception e) {
            throw new ServiceException(ResultCode.OPERATION_FAILED, e);
        }
    }
}
