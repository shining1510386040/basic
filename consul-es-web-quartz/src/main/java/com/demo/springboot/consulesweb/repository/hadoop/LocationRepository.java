package com.demo.springboot.consulesweb.repository.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Hbase 仓储
 * @date 2021/2/23 20:10
 * @see
 */
@Repository
public class LocationRepository implements InitializingBean {

    private static final Logger log = LogManager.getLogger(LocationRepository.class);

    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Resource(name = "hbaseConfiguration")
    private Configuration config;
    private HBaseAdmin admin;

    public static final String gps_table_basic = "lo_";
    public static byte[] CF_INFO = Bytes.toBytes("lf");

    @Override
    public void afterPropertiesSet() throws Exception {
        admin = new HBaseAdmin(config);
    }

    /**
     * 数据发送到 Hbase数据库中
     * 1.自定义业务前缀+ （一天的总毫秒数- 当前的时间在一天中的毫秒数） //得到最新数据总是在最前面的hbase表
     * 2.使用map的层级结构自动映射为hbase的列族和列名
     */
    public byte[] saveObjSimpleFun(final HashMap<String, LinkedHashMap<String, Object>> srcData, final String businessID, final String tableName) {
        LocalDateTime now = LocalDateTime.now();
        byte[] execute = hbaseTemplate.execute(tableName, table -> {
            byte[] add = Bytes.add(Bytes.toBytes(businessID), Bytes.toBytes(Integer.MAX_VALUE - now.get(ChronoField.MILLI_OF_DAY)));
            Put putObj = new Put(add);
            srcData.forEach((cf_name, file_map)
                    -> file_map.forEach((filed_key, filed_value) -> {
                byte[] family = CF_INFO;
//                log.debug("convert filed code is {}", filed_key);
                if (filed_value != null) {

                    byte[] value = toBytes(filed_value);
                    if (value.length > 0) {
                        putObj.addColumn(family, Bytes.toBytes(filed_key), value);
                    }
                }
            }));
            table.put(putObj);
            return add;
        });
        log.debug("end of save data to hbase .....");
        return execute;
    }

    public byte[] toBytes(Object obj) {
        byte[] result = new byte[]{};
        if (obj != null) {
            if (obj instanceof String) {
                result = Bytes.toBytes((String) obj);
            } else if (obj instanceof Integer) {
                result = Bytes.toBytes((Integer) obj);
            } else if (obj instanceof Date) {
                result = Bytes.toBytes(obj.toString());
            } else if (obj instanceof Short) {
                result = Bytes.toBytes((Short) obj);
            } else if (obj instanceof Long) {
                result = Bytes.toBytes((Long) obj);
            } else if (obj instanceof Double) {
                result = Bytes.toBytes((Double) obj);
            } else if (obj instanceof Float) {
                result = Bytes.toBytes((Float) obj);
            } else if (obj instanceof Boolean) {
                result = Bytes.toBytes((Boolean) obj);
            } else if (obj instanceof BigDecimal) {
                result = Bytes.toBytes((BigDecimal) obj);
            } else if (obj instanceof ByteBuffer) {
                result = Bytes.toBytes((ByteBuffer) obj);
            } else if (obj instanceof ArrayList) {
                result = convertArrayList(obj);
            } else {
                System.out.println("invalid Data Type....." + obj.toString());
            }
        }
        return result;
    }

    private byte[] convertArrayList(Object object) {
        ArrayList<?> arrayList = (ArrayList) object;
        final byte[] result = new byte[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            Object o = arrayList.get(i);
            if (o instanceof Integer) {
                result[i] = (byte) (int) o;
            } else {
                System.out.println("error convertArrayList");
            }
        }
        return result;
    }
}