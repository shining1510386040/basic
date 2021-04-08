package com.demo.springboot.es7withresthighlevelclient.service.util;

import java.io.IOException;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 操作ES 的通用接口
 * @date 2021/4/8 10:10
 * @see
 */
public interface ElasticSearchMapper<T extends BaseElasticSearchEntity> {

    /**
     * 创建ES索引库
     *
     * @param index   index
     * @param mapping 映射，为json字符串，
     *                例如：{\"properties\":{\"name\":{\"type\":\"keyword\"},\"date1\":{\"type\":\"date\"},\"date2\":{\"type\":\"date\"},\"updateTime\":{\"type\":\"date\"}}}
     * @return 是否创建成功
     * @throws IOException 异常
     */
    boolean createIndex(String index,String mapping) throws IOException;

    /**
     * 删除索引
     *
     * @param index index
     * @return 是否删除成功
     * @throws IOException 异常
     */
    boolean deleteIndex(String index) throws IOException;

    /**
     * 根据id查询数据是否存在于ES中
     *
     * @param entry index和type和id
     * @return 是否存在
     */
    boolean isExists(T entry);

    /**
     * 插入ES，指定id
     *
     * @param entry 实体
     * @return 插入结果
     */
    String insert(T entry);

    /**
     * 更新ES，指定id
     *
     * @param entry 实体
     * @return 更新结果
     */
    String update(T entry);

    /**
     * 删除ES，指定id
     *
     * @param entry index和type和id
     * @return 删除结果
     */
    String delete(T entry);

    /**
     * 根据id查询ES
     *
     * @param entry index和type和id
     * @return 查询数据结果json字符串
     */
    String selectById(T entry);

    /**
     * 多条件查询，正序排序(分页)
     *
     * @param entry     封装的查询条件
     * @param sortField 排序字段的字段名，如：updateTime
     * @param page      页码
     * @param length    每页条数
     * @return 查询结果，es封装
     */
    ElasticSearchResponseEntity selectByMultiConditionAsc(T entry, String sortField, Integer page, Integer length);

    /**
     * 多条件查询，倒序排序（分页）
     *
     * @param entry     封装的查询条件
     * @param sortField 排序字段的字段名，如：updateTime
     * @param page      页码
     * @param length    每页条数
     * @return 查询结果，es封装
     */
    ElasticSearchResponseEntity selectByMultiConditionDesc(T entry, String sortField, Integer page, Integer length);

}
