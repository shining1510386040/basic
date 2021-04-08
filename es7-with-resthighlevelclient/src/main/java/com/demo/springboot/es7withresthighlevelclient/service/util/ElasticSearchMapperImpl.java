package com.demo.springboot.es7withresthighlevelclient.service.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description es 操作接口实现类
 * @date 2021/4/8 10:15
 * @see
 */
@Service
@Slf4j
public class ElasticSearchMapperImpl<T extends BaseElasticSearchEntity> implements ElasticSearchMapper<T> {

    @Autowired
    @Qualifier("myEs7Client")
    protected RestHighLevelClient restClient;

    @Override
    public boolean createIndex(String index,String mapping) throws IOException {

        CreateIndexRequest indexRequest = new CreateIndexRequest(index);
        indexRequest.mapping(mapping, XContentType.JSON);
        IndicesClient indicesClient = restClient.indices();
        CreateIndexResponse createIndexResponse = indicesClient.create(indexRequest, RequestOptions.DEFAULT);
        return createIndexResponse.isAcknowledged();
    }

    @Override
    public boolean deleteIndex(String index) throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        IndicesClient indicesClient = restClient.indices();
        AcknowledgedResponse delete = indicesClient.delete(deleteIndexRequest, RequestOptions.DEFAULT);
        return delete.isAcknowledged();
    }

    @Override
    public boolean isExists(T entry) {
//        GetRequest getRequest = new GetRequest(entry.getEsIndex(), entry.getEsType(), entry.getEsId());
        // es7 中默认的 type 为 _doc
        GetRequest getRequest = new GetRequest(entry.getEsIndex(), entry.getEsId());
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        try {
            boolean exists = restClient.exists(getRequest, RequestOptions.DEFAULT);
            log.info("查询ES是否存在数据，isExists：{}，id：{}", exists, getRequest.id());
            return exists;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String insert(T entry) {
//        IndexRequest request = new IndexRequest(entry.getEsIndex(), entry.getEsType(), entry.getEsId());
        IndexRequest request = new IndexRequest(entry.getEsIndex());
        // 指定_id
        request.id(entry.getEsId());
        // 指定_source
        request.source(JSONObject.toJSONString(entry), XContentType.JSON);
        request.create(true);
        IndexResponse response;
        try {
            response = restClient.index(request, RequestOptions.DEFAULT);
            String name = response == null ? null : response.getResult().name();
            log.info("ES执行插入：index：{}，type：{}，id：{}", request.index(), request.type(), request.id());
            return name;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String update(T entry) {
//        UpdateRequest request = new UpdateRequest(entry.getEsIndex(), entry.getEsType(), entry.getEsId());
        UpdateRequest request = new UpdateRequest(entry.getEsIndex(), entry.getEsId());
        request.doc(JSONObject.toJSONString(entry), XContentType.JSON);
        UpdateResponse response;
        try {
            response = restClient.update(request, RequestOptions.DEFAULT);
            String name = response == null ? null : response.getResult().name();
            log.info("ES执行更新：index：{}，type：{}，id：{}", request.index(), request.type(), request.id());
            return name;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String delete(T entry) {
        DeleteRequest request = new DeleteRequest(entry.getEsIndex(), entry.getEsId());
        try {
            DeleteResponse response = restClient.delete(request, RequestOptions.DEFAULT);
            String name = response == null ? null : response.getResult().name();
            log.info("ES执行删除：index：{}，type：{}，id：{}", request.index(), request.type(), request.id());
            return name;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String selectById(T entry) {
        GetRequest request = new GetRequest(entry.getEsIndex(), entry.getEsId());
        try {
            GetResponse response = restClient.get(request, RequestOptions.DEFAULT);
            if (response.isExists()) {
                return response.getSourceAsString();
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public ElasticSearchResponseEntity selectByMultiConditionDesc(T entry, String sortField, Integer page, Integer length) {
        return packageSelectData(selectByMultiCondition(entry, sortField, SortOrder.DESC, page, length));
    }

    @Override
    public ElasticSearchResponseEntity selectByMultiConditionAsc(T entry, String sortField, Integer page, Integer length) {
        return packageSelectData(selectByMultiCondition(entry, sortField, SortOrder.ASC, page, length));
    }

    /**
     * 封装成ElasticSearchResponseEntity返回
     *
     * @param searchHits es查询的数据
     * @return ElasticSearchResponseEntity
     */
    private ElasticSearchResponseEntity packageSelectData(SearchHits searchHits) {
        if (searchHits == null || searchHits.getTotalHits().value < 1) {
            return new ElasticSearchResponseEntity(0L, new LinkedList<>());
        }
        List<String> list = new LinkedList<>();
        Arrays.stream(searchHits.getHits()).forEach(hit -> {
            String source = hit.getSourceAsString();
            log.debug("ES查询数据：{}", source);
            list.add(source);
        });
        return new ElasticSearchResponseEntity(searchHits.getTotalHits().value, list);
    }

    /**
     * 多条件查询，包含排序规则和分页功能
     *
     * @param entry     封装的查询条件
     * @param sortField 排序字段的字段名
     *                  如果在mapping中未指定排序字段的类型为精确类型（如date或keyword），则此排序字段必须加上 .keyword 后缀，表示精准匹配，否则报错：[type=search_phase_execution_exception, reason=all shards failed]
     *                  例如：username.keyword，如果mapping指定了字段类型为date或者keyword，则直接传字段名即可，例如：keywordName1、updateTime
     * @param sortOrder 排序规则，正序还是倒序
     * @return 查询结果，es封装
     */
    private SearchHits selectByMultiCondition(T entry, String sortField, SortOrder sortOrder, Integer page, Integer length) {
        SearchRequest searchRequest = new SearchRequest(entry.getEsIndex());
//        searchRequest.types(entry.getEsType());
        try {
            BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
            entry.packageElasticSearchBody(boolBuilder);
            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
            sourceBuilder.sort(new FieldSortBuilder(sortField).order(sortOrder));
            sourceBuilder.query(boolBuilder);
            setPaging(sourceBuilder, page, length);
            searchRequest.source(sourceBuilder);
            SearchResponse searchResponse = restClient.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse.getHits();
        } catch (Exception e) {
            log.error("===》 es【多条件】查询，报错！entry={}", searchRequest.source().toString(), e);
            return null;
        }
    }

    /**
     * 设置es查询的分页配置
     * <p>
     * ElasticSearchConstant.MAX_SIZE = ES默认10000条，这个自定义的常量无法超过默认值
     *
     * @param sourceBuilder builder
     * @param page          页码
     * @param length        每页条数
     */
    private void setPaging(SearchSourceBuilder sourceBuilder, Integer page, Integer length) {
        if (page != null && length != null) {
            length = Math.min(length, ElasticSearchConstant.MAX_SIZE);
            int from = (page - 1) * length;
            sourceBuilder.from(from);
            sourceBuilder.size(length);
        }
    }
}
