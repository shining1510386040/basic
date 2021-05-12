package com.demo.springboot.springlucence.controller;

import com.demo.springboot.springlucence.entity.WorkOrderRecord;
import com.demo.springboot.springlucence.service.LuceneService;
import com.demo.springboot.springlucence.utils.CommonUtils;
import com.demo.springboot.springlucence.utils.RestUtils;
import com.demo.springboot.springlucence.vo.PageQuery;
import com.demo.springboot.springlucence.vo.ServiceResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试
 * @date 2021/4/26 14:36
 * @see
 */
@RestController
@RequestMapping("/lucene")
public class TestController {

    @Autowired
    private RestUtils restUtils;

    @Autowired
    private LuceneService luceneService;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 创建索引
     * @date 2021/4/26 15:10
     */
    @GetMapping("/create")
    public void createLuceneIndex() throws IOException {
        // 1.查询数据
        String url = "http://localhost:8001/workOrder/page";
        MultiValueMap params = new LinkedMultiValueMap(4);
        params.add("pageNo", 1);
        params.add("pageSize", 40);
        String accessToken = "postsale20211c67e72d";
        ServiceResult ret = restUtils.sendPostRequest(url, params, accessToken);
        Object data = ret.getData();
        // 2.创建索引
        List<LinkedHashMap> mapList = (List<LinkedHashMap>) LinkedHashMap.class.cast(data).get("result");
        luceneService.createWorkOrderRecordIndex(mapList);
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 搜索
     * @date 2021/4/26 15:12
     */
    @PostMapping("/search")
    public ServiceResult search(@RequestParam String keyword) throws Exception {
        PageQuery pageQuery = new PageQuery();
        Map<String, String> queryParams = new HashMap<>(3);
        queryParams.put("keyword", keyword);
        pageQuery.setQueryParam(queryParams);

        PageInfo<Object> of = PageInfo.of(new ArrayList<>());
        pageQuery.setPageInfo(of);
        PageQuery searchWorkOrderRecord = luceneService.searchWorkOrderRecord(pageQuery);
        ServiceResult ret = new ServiceResult("200", "检索成功");
        ret.setData(searchWorkOrderRecord.getResults());
        return ret;
    }

}
