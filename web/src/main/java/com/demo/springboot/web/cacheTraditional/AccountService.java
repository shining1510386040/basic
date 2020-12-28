package com.demo.springboot.web.cacheTraditional;

import com.demo.springboot.web.entity.Account;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 查询账号服务
 * @date 2020/12/28 12:44
 * @see
 */
@Service
public class AccountService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 缓存上下文
     */
    @Resource
    private CacheContext<List<Map<String, Object>>> accountCacheContext;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查询并缓存结果
     * @date 2020/12/28 15:29
     */
    public List<Map<String, Object>> getAccoutByName(String username) {
        // 缓存逻辑：先查询缓存，缓存命中直接返回，缓存不命中，查db，结果放入缓存
        List<Map<String, Object>> ret = accountCacheContext.get(username);
        if (ret != null) {
            logger.warn("get data from cache:" + username);
            return ret;
        } else {
            logger.error("=====================>>>>传统方式使用缓存：进入查询db");
            String sql = "select * from account where username like ?";
            Object[] args = new Object[]{username + "%"};
            List<Map<String, Object>> data = jdbcTemplate.queryForList(sql, args);
            accountCacheContext.addOrUpdateCache(username, data);
            return data;
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 清除缓存的结果
     * @date 2020/12/28 15:29
     */
    public void reload(String username) {
        accountCacheContext.evictCache(username);
    }

}
