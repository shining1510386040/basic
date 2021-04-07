package com.demo.springboot.es7withresthighlevelclient.service;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 操作es7 的接口
 * @date 2021/4/7 17:16
 * @see
 */
public interface Es7Service {
    /**
     * 创建索引库
     */
    void createIndexRequest(String index);

    /**
     * 删除索引库
     */
    void deleteIndexRequest(String index);

    /**
     * 更新索引文档
     */
    void updateRequest(String index, String id, Object object);

    /**
     * 新增索引文档
     */
    void insertRequest(String index, String id, Object object);

    /**
     * 删除索引文档
     */
    void deleteRequest(String index, String id);
}
