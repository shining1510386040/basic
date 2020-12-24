package com.demo.springboot.web.mongo;

import com.demo.springboot.web.entity.Userinfo;
import com.demo.springboot.web.utils.BeanUtils;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 操作mongo的controller
 * @date 2020/12/23 18:34
 * @see
 */
@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private MongoTemplate mongoTemplate;


    @PostMapping("/save")
    public UserInfo saveData(UserInfo data) {
        // 连续调用两次save 相同的对象会报错，mongo只会生成一个_id
//        UserInfo save = mongoTemplate.save(data);
        UserInfo save1 = BeanUtils.clone(data);
        UserInfo save2 = BeanUtils.clone(data);
        UserInfo save_replica = mongoTemplate.save(save1, "userinfo-replica");
//        UserInfo insert = mongoTemplate.insert(save2);
        return data;
    }

    @PostMapping("/delete")
    public long deleteData(String id) {
        UserInfo data = new UserInfo();
        data.setId(id);

        DeleteResult remove = mongoTemplate.remove(data);
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        DeleteResult deleteResult_re = mongoTemplate.remove(query, "userinfo_replica");
        return remove.getDeletedCount();
    }

    @PostMapping("/update")
    public long updateData(UserInfo data) {
        // 找到主键
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(data.getId()));
        // 更新的值
        Update update = new Update();
        update.set("username", data.getUsername());
        update.set("password", data.getPassword());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, "userinfo");

        return updateResult.getMatchedCount();
    }

    @GetMapping("/find")
    public UserInfo findOne(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        UserInfo one = mongoTemplate.findOne(query, UserInfo.class);
        return one;
    }

    @PostMapping("/findAll")
    public List<UserInfo> findAll(UserInfo search) {
        // 查询所有
        List<UserInfo> all = mongoTemplate.findAll(UserInfo.class);
        // 查到匹配的并删除
        Query query = new Query();
        query.addCriteria(Criteria.byExample(search));
        List<UserInfo> allAndRemove = mongoTemplate.findAllAndRemove(query, UserInfo.class, "userinfo_replica");
        List<UserInfo> userInfos = mongoTemplate.find(query, UserInfo.class);
        return userInfos;
    }

}
