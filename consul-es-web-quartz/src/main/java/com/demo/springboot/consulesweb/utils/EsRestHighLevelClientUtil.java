package com.demo.springboot.consulesweb.utils;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description es操作工具类
 * @date 2021/1/26 14:44
 * @see
 */
@Component
public class EsRestHighLevelClientUtil {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    // todo ...




}
