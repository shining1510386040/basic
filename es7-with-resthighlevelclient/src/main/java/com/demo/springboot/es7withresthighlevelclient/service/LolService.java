package com.demo.springboot.es7withresthighlevelclient.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.demo.springboot.es7withresthighlevelclient.entity.Lol;
import com.demo.springboot.es7withresthighlevelclient.service.impl.Es7ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Lol实体操作服务
 * @date 2021/4/7 17:23
 * @see
 */
@Service
@Slf4j
public class LolService extends Es7ServiceImpl {

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 批量插入文档（index）
     * @date 2021/4/7 17:24
     */
    public void insertBatch(String index, List<Lol> list) {
        if (list.isEmpty()) {
            log.warn("bach insert index but list is empty ...");
            return;
        }
        list.forEach((lol) -> {
            super.insertRequest(index, lol.getId().toString(), lol);
        });
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 检索
     * @date 2021/4/7 17:25
     */
    public List<Lol> searchList(String index) {

        SearchResponse searchResponse = search(index);

        SearchHit[] hits = searchResponse.getHits().getHits();
        List<Lol> lolList = new ArrayList<>();
        Arrays.stream(hits).forEach(hit -> {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Lol lol = BeanUtil.mapToBean(sourceAsMap, Lol.class, true);
            lolList.add(lol);
        });
        return lolList;
    }

    /**
     * @param index 要搜索的索引库
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查询（搜索）
     * @date 2021/4/9 17:23
     */
    protected SearchResponse search(String index) {

        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 短语匹配
        // match_all
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //bool 多条件复合查询
        //BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder()
        //        .filter(QueryBuilders.matchQuery("name", "盖伦"))
        // must 表示 and并且；should 表示 or 或者
        //        .must(QueryBuilders.matchQuery("desc", "部落"))
        //        .should(QueryBuilders.matchQuery("realName", "光辉"));
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().
//                // match 短语匹配；must->and
//                        must(QueryBuilders.matchQuery("name", "亚瑟"))
//                .must(QueryBuilders.matchQuery("name", "亚瑟"))
//                // should ->or
//                .should(QueryBuilders.matchQuery("name", "亚瑟"));
//
        //分页
        //searchSourceBuilder.from(1).size(2);
        // 排序
        //searchSourceBuilder.sort("", SortOrder.DESC);

        ////误拼写时的fuzzy模糊搜索方法 2表示允许的误差字符数
        //QueryBuilders.fuzzyQuery("title", "ceshi").fuzziness(Fuzziness.build("2"));
        searchRequest.source(searchSourceBuilder);
        System.out.println(searchSourceBuilder.toString());
        System.out.println(JSONUtil.parseObj(searchSourceBuilder.toString()).toStringPretty());
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return searchResponse;
    }
}
