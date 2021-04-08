package com.demo.springboot.es7withresthighlevelclient.service.util;

import org.elasticsearch.index.query.*;

import java.util.Collection;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description ES查询条件builder
 * @date 2021/4/8 10:17
 * @see
 */
public class QueryBuilder {

    /**
     * 大于等于
     */
    public static final String GTE = "gte";
    /**
     * 小于等于
     */
    public static final String LTE = "lte";
    /**
     * 大于
     */
    public static final String GT = "gt";
    /**
     * 小于
     */
    public static final String LT = "lt";

    /**
     * 不会对搜索词进行分词处理，而是作为一个整体与目标字段进行匹配，
     * 若完全匹配，则可查询到。
     *
     * @param key
     * @param value
     * @return
     */
    public static TermQueryBuilder term(String key, Object value) {
        return QueryBuilders.termQuery(key, value);
    }

    /**
     * 一次匹配多个值，即 in()查询
     *
     * @param key    key
     * @param values 值集合
     * @return TermsQueryBuilder
     */
    public static TermsQueryBuilder terms(String key, Collection<?> values) {
        return QueryBuilders.termsQuery(key, values);
    }

    /**
     * 一次匹配多个值，即 in()查询，keyword全值匹配，精确查询
     *
     * @param key
     * @param values
     * @return
     */
    public static TermsQueryBuilder termsKeyword(String key, Collection<?> values) {
        return QueryBuilders.termsQuery(key + ".keyword", values);
    }


    /**
     * 会将搜索词分词，再与目标查询字段进行匹配，
     * 若分词中的任意一个词与目标字段匹配上，则可查询到。
     *
     * @param key
     * @param value
     * @return
     */
    public static MatchQueryBuilder match(String key, Object value) {
        return QueryBuilders.matchQuery(key, value);
    }

    /**
     * 分词模糊查询
     *
     * @param key
     * @param value
     * @return
     */
    public static FuzzyQueryBuilder fuzzy(String key, Object value) {
        return QueryBuilders.fuzzyQuery(key, value);
    }

    /**
     * 范围查询
     *
     * @param key
     * @param value
     * @return
     */
    public static RangeQueryBuilder range(String key, Object value, String rangeType) {
        RangeQueryBuilder builder = QueryBuilders.rangeQuery(key);
        switch (rangeType) {
            case GTE:
                return builder.gte(value);
            case LTE:
                return builder.lte(value);
            case GT:
                return builder.gt(value);
            case LT:
                return builder.lt(value);
            default:
                return builder.gt(value);
        }
    }
}
