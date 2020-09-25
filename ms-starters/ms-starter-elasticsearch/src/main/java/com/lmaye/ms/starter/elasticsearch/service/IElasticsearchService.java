package com.lmaye.ms.starter.elasticsearch.service;

import com.lmaye.ms.core.exception.ServiceException;
import com.lmaye.ms.core.query.ListQuery;
import com.lmaye.ms.core.query.PageQuery;
import com.lmaye.ms.core.query.Query;
import com.lmaye.ms.starter.elasticsearch.repository.IElasticsearchRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * -- Elasticsearch Service
 *
 * @author lmay.Zhou
 * @date 2020/9/25 16:31 星期五
 * @since Email: lmay_zlm@meten.com
 */
public interface IElasticsearchService<T, ID extends Serializable> {
    /**
     * 获取对应 entity 的 IElasticsearchRepository
     *
     * @return IElasticsearchRepository
     */
    IElasticsearchRepository<T, ID> getRepository();

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     * @throws ServiceException operate exception
     */
    <S extends T> S save(S entity) throws ServiceException;

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null}.
     * @return the saved entities will never be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     * @throws ServiceException         operate exception
     */
    <S extends T> Iterable<S> saveAll(Iterable<S> entities) throws ServiceException;

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     * @throws ServiceException         operate exception
     */
    void deleteById(ID id) throws ServiceException;

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws ServiceException operate exception
     */
    Optional<T> findById(ID id) throws ServiceException;

    /**
     * Returns a {@link Iterable} of entities matching the given {@link QueryBuilder}.In case no match could be found, an empty
     * {@link Iterable} is returned.
     *
     * @param query must not be {@literal null}.
     * @return a {@link Iterable} of entities matching the given {@link QueryBuilder}.
     * @throws ServiceException operate exception
     */
    Iterable<T> search(QueryBuilder query) throws ServiceException;

    /**
     * Returns a {@link Page} of entities matching the given {@link QueryBuilder}.In case no match could be found, an empty
     * {@link Page} is returned.
     *
     * @param query    must not be {@literal null}.
     * @param pageable Pageable
     * @return a {@link Page} of entities matching the given {@link QueryBuilder}.
     * @throws ServiceException operate exception
     */
    Page<T> search(QueryBuilder query, Pageable pageable) throws ServiceException;

    /**
     * Returns a {@link Page} of entities matching the given {@link SearchQuery}.In case no match could be found, an empty
     * {@link Page} is returned.
     *
     * @param searchQuery must not be {@literal null}.
     * @return a {@link Page} of entities matching the given {@link SearchQuery}.
     * @throws ServiceException operate exception
     */
    Page<T> search(SearchQuery searchQuery) throws ServiceException;

    /**
     * Returns all entities matching the given {@link Query}. In case no match could be found an empty {@link List}
     * is returned.
     *
     * @param query must not be {@literal null}.
     * @return a {@link List} of entities matching the given {@link Query}.
     * @throws ServiceException operate exception
     */
    List<T> findAll(Query query) throws ServiceException;

    /**
     * Returns a {@link Page} of entities matching the given {@link Query}.In case no match could be found, an empty
     * {@link Page} is returned.
     *
     * @param query may be {@literal null}.
     * @return a {@link List} of entities matching the given {@link Query}.
     * @throws ServiceException operate exception
     */
    List<T> findAll(ListQuery query) throws ServiceException;

    /**
     * Returns a {@link Page} of entities matching the given {@link Query}.In case no match could be found, an empty
     * {@link Page} is returned.
     *
     * @param query must not be {@literal null}.
     * @return a {@link Page} of entities matching the given {@link Query}.
     * @throws ServiceException operate exception
     */
    Page<T> findAll(PageQuery query) throws ServiceException;
}
