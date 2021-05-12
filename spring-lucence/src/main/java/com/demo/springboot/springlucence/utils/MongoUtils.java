package com.demo.springboot.springlucence.utils;

import com.demo.springboot.springlucence.entity.WorkOrderCategory;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description mongo工具类
 * @date 2021/4/26 18:50
 * @see
 */
@Component
public class MongoUtils {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 大数据量下：mongotemplate的find() 查询太慢====》》 采用原生查询
     */
    public List<WorkOrderCategory> listData() {
//        Query query = Query.query(new BasicQuery());
        List<WorkOrderCategory> all = mongoTemplate.findAll(WorkOrderCategory.class);
        return all;
    }


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description mongo大数据量下 这种查询较快
     * @date 2021/4/26 19:06
     */
    public List<WorkOrderCategory> listDataNative() {

        BasicDBObject query = new BasicDBObject();
        // 组装原生查询条件
//        query.put("id", new BasicDBObject("$in", idList));
//        query.put("time", (new BasicDBObject("$gte", startTime)).append("$lte", endTime));
        FindIterable<Document> workOrderCategory = mongoTemplate.getCollection("WorkOrderCategory").find(query);

        MongoCursor<Document> iterator = workOrderCategory.iterator();
        List<WorkOrderCategory> ret = new ArrayList<>(14);
        while (iterator.hasNext()) {
            Document next = iterator.next();
            Object o = next.get("");
            WorkOrderCategory cur = new WorkOrderCategory();
            // 属性赋值 todo 。。
            cur.setCategoryName(next.get("categoryName") + "");
            ret.add(cur);
        }
        return ret;
    }


}
