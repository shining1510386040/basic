package com.demo.springboot.consulesweb.service.impl;

import com.demo.springboot.consulesweb.service.HBaseService;
import com.demo.springboot.consulesweb.utils.HbaseTable;
import com.demo.springboot.consulesweb.utils.Result;
import com.demo.springboot.consulesweb.utils.ResultGenerator;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description HBase服务实现类
 * <p>
 * Result是自定义的一个参数统一返回对象，里面就是message、code、data。
 * ResultGenerator是封装的生成Result基本信息的工具类。
 * HbaseTable是我定义的一个建表的入参对象，里面是表名、List<Map<String, String>>接收family的。
 * hbase的建表与其他关系型数据库不太一样，建表时不需要建列。基本上集成就完成了，这里说下这样集成的弊端吧。
 * 首先spring-data-hadoop-boot在2019-04-09已经停止维护了，原因没有说。停更时支持的最高版本：hadoop：2.7.3，hbase：2.1。具体可以去spring官网看，有博友说使用到2.7.5 + 2.1，我没有亲测。因为我们后期时微服务，我测试springBoot与SpringCloud的版本最高就是我pom里的版本，再高就报错了额，想改来着，看到要改的地方太多，不是一人之力可以做的，放弃了。如果是自己玩大数据平台，图方便就可以用这套版本。
 * 我们这次是从kafak消费，存Hbase库。
 * @date 2021/2/23 20:28
 * @see
 */
@Service
public class HBaseServiceImpl implements HBaseService {

    @Autowired
    private HbaseTemplate hbaseTemplate;


    @Override
    public Result createTable(HbaseTable hbaseTable) throws Exception {

        //从hbase模板获取配置
        Configuration conf = hbaseTemplate.getConfiguration();

        //连接
        Connection connection = null;
        Table table = null;
        try {
            //获取连接
            connection = ConnectionFactory.createConnection(conf);

            //从连接获取一个客户端
            Admin admin = connection.getAdmin();

            //组织建表
            String tableName = hbaseTable.getTableName();
            List<Map<String, String>> columnMpList = hbaseTable.getColumnMapList();
            if (!admin.tableExists(TableName.valueOf(tableName))) {

                HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));

                for (Map<String, String> mp : columnMpList) {
                    Set<String> keySet = mp.keySet();
                    Iterator<String> it = keySet.iterator();
                    while (it.hasNext()) {
                        String key = it.next();
                        if ("colEn".equals(key)) {
                            String colEn = mp.get(key);
                            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(Bytes.toBytes(colEn));
                            tableDescriptor.addFamily(hColumnDescriptor);
                        }
                    }
                }

                //客户端创建表
                admin.createTable(tableDescriptor);
            } else {
                return ResultGenerator.genFailResult(tableName + "表已经存在");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.getCause().getMessage());
        }


        return ResultGenerator.genSuccessResult();
    }
}
