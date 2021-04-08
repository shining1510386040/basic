package com.demo.springboot.es7withresthighlevelclient.entity;

import com.demo.springboot.es7withresthighlevelclient.service.util.BaseElasticSearchEntity;
import com.demo.springboot.es7withresthighlevelclient.service.util.QueryBuilder;
import com.demo.springboot.es7withresthighlevelclient.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试ES实体。学生
 * @date 2021/4/8 11:14
 * @see
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseElasticSearchEntity {

    private Long id;
    private String name;
    private Date birth;
    private String nikename;

    public Student(String esIndex) {
//        super.setEsIndex(esIndex);
        super();
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 重写父类方法，封装查询的条件
     * @date 2021/4/8 11:20
     */
    @Override
    public void packageElasticSearchBody(BoolQueryBuilder boolQueryBuilder) {
        // 相当于IN查询
        if (!StringUtils.isEmpty(this.name)) {
            List<String> nameList = new ArrayList<>(Arrays.asList(this.name.split(",")));
            boolQueryBuilder.must(QueryBuilder.termsKeyword("name", nameList));
        }
        // 日期范围查询，日期存入yyyy-MM-dd HH:mm:ss格式时配置不当可能会报错，建议存入时间戳
        if (!StringUtils.isEmpty(this.startTime)) {
            boolQueryBuilder.must(QueryBuilder.range("birth", DateUtil.convertTimeToLong(this.startTime, DateUtil.YYYY_MM_DD_HH_MM_SS), QueryBuilder.GTE));
        }
        if (!StringUtils.isEmpty(this.endTime)) {
            boolQueryBuilder.must(QueryBuilder.range("birth", DateUtil.convertTimeToLong(this.endTime, DateUtil.YYYY_MM_DD_HH_MM_SS), QueryBuilder.LTE));
        }
        // match精准查询
        if (!StringUtils.isEmpty(this.nikename)) {
            boolQueryBuilder.must(QueryBuilder.match("nikename", "zhang"));
        }
        // 其他查询要求可以自行百度

    }
}
