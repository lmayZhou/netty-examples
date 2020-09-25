package com.lmaye.ms.starter.elasticsearch.utils;

import com.lmaye.ms.core.query.Query;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

/**
 * -- Elasticsearch Utils
 *
 * @author lmay.Zhou
 * @date 2020/9/25 16:12 星期五
 * @since Email: lmay_zlm@meten.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ElasticsearchUtils {
    /**
     * 转化请求查询对象为QueryBuilder
     *
     * @param query QueryBuilder
     * @return QueryBuilder
     */
    public static QueryBuilder convert(Query query) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!Objects.isNull(query) && !query.isNull()) {
            boolQueryBuilder.must(convertQuery(query));
        }
        Query must = query.getMust();
        // 且操作
        while (!Objects.isNull(must)) {
            if (!must.isNull()) {
                boolQueryBuilder.must(convertQuery(must));
            }
            must = must.getMust();
        }
        Query should = query.getShould();
        // 或操作
        while (!Objects.isNull(should)) {
            if (!should.isNull()) {
                boolQueryBuilder.should(convertQuery(should));
            }
            should = should.getShould();
        }
        return boolQueryBuilder;
    }

    /**
     * 转换查询
     *
     * @param query QueryBuilder
     * @return QueryBuilder
     */
    private static QueryBuilder convertQuery(Query query) {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 等于查询
        if (!CollectionUtils.isEmpty(query.getTerms())) {
            query.getTerms().forEach(q -> {
                if (q.isNegation()) {
                    boolQueryBuilder.mustNot(QueryBuilders.termQuery(q.getField(), q.getValue()));
                } else {
                    boolQueryBuilder.must(QueryBuilders.termQuery(q.getField(), q.getValue()));
                }
            });
        }
        // 模糊查询
        if (!CollectionUtils.isEmpty(query.getMatches())) {
            query.getMatches().forEach(q -> {
                if (q.isNegation()) {
                    boolQueryBuilder.mustNot(QueryBuilders.matchQuery(q.getField(), q.getValue()));
                } else {
                    boolQueryBuilder.must(QueryBuilders.matchQuery(q.getField(), q.getValue()));
                }
            });
        }
        // 范围查询
        if (!CollectionUtils.isEmpty(query.getRanges())) {
            query.getRanges().forEach(q -> {
                if (q.isNegation()) {
                    boolQueryBuilder.mustNot(QueryBuilders.rangeQuery(q.getField()).gte(q.getGe()).lte(q.getLe()));
                } else {
                    boolQueryBuilder.must(QueryBuilders.rangeQuery(q.getField()).gte(q.getGe()).lte(q.getLe()));
                }
            });
        }
        // in查询
        if (!CollectionUtils.isEmpty(query.getIns())) {
            query.getIns().forEach(q -> {
                if (q.isNegation()) {
                    boolQueryBuilder.mustNot(QueryBuilders.termsQuery(q.getField(), q.getValues()));
                } else {
                    boolQueryBuilder.must(QueryBuilders.termsQuery(q.getField(), q.getValues()));
                }
            });
        }
        return boolQueryBuilder;
    }
}
