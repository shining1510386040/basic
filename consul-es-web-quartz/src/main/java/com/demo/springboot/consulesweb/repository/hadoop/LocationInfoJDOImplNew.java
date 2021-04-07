package com.demo.springboot.consulesweb.repository.hadoop;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Hbase 查询
 * @date 2021/2/23 20:07
 * @see
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: 实现Hbase部分查询
 */
@Repository("nlocationInfoJDOImplNew")
public class LocationInfoJDOImplNew implements InitializingBean {

    private static final Logger log = LogManager.getLogger(LocationInfoJDOImplNew.class);
    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Resource(name = "hbaseConfiguration")
    private Configuration config;
    private HBaseAdmin admin;

    private static final String cf_name = "lf";

    /**
     * 从hbase中获取查询结果
     */
    public List<Object> queryLocDataByTable(List<String> rowkeyPrefix, Date startTime, Date endTime) {

        String tableName = "";
        String cf_name = "demo";
        byte[] cf_bytes = Bytes.toBytes(cf_name);

        tableName = "lo_20170309";
        Scan scan = new Scan();
        scan.setCaching(50);//全表扫描设置cache
        scan.addFamily(cf_name.getBytes());
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ONE);
        try {
            //时间戳，过滤器
            scan.setTimeRange(startTime.getTime(), endTime.getTime());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //批量增加过滤条件 ，使用行键的PrefixFilter进行过滤
        rowkeyPrefix.forEach(s -> {
            filterList.addFilter(new PrefixFilter(Bytes.toBytes(s)));
        });
        scan.setFilter(filterList);
        //java8 特性 , 可以通过该方法设定返回值来实现

        List<Object> entities = hbaseTemplate.find(tableName, scan, results -> {
            List<Object> objectList = new ArrayList<>();
            Iterator<Result> iterator = results.iterator(); //这个迭代器只能获取一次
            while (iterator.hasNext()) {
                //迭代获取数据
//                        SimpleLocationEntity temp = new SimpleLocationEntity();
                Result next = iterator.next();
                next.getFamilyMap(cf_bytes).forEach((bytes, values) -> {
                    byte[] row = next.getRow();
                    System.out.println("rowKey:" + Bytes.toString(next.getRow(), 0, 7) + Bytes.toIntUnsafe(row, 7));
                });
                objectList.add(next);
                log.debug("get one recode");
            }
            return objectList;
        });
        return entities;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Configuration configuration = config;
        admin = new HBaseAdmin(configuration);
    }
}