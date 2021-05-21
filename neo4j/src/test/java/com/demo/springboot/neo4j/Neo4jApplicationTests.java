package com.demo.springboot.neo4j;

import com.demo.springboot.neo4j.entity.CompanyGraph;
import com.demo.springboot.neo4j.entity.SupplyGraph;
import com.demo.springboot.neo4j.entity.SupplyRelationship;
import com.demo.springboot.neo4j.repository.SupplyGraphRepository;
import com.demo.springboot.neo4j.repository.SupplyRelationshipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Neo4jApplicationTests {

    @Autowired
    private SupplyGraphRepository supplyGraphRepository;

    @Autowired
    private SupplyRelationshipRepository supplyRelationshipRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSave() {
        //采购占比
        String scale = "47.14%";
        // 采购金额
        String amount = "18923.42";
        //供应商名称
        String name = "常州常电及其关联公司";
        //公司实体部分及添加公司节点部分省略...(companyGraph)
        SupplyGraph supplyGraph = SupplyGraph.builder().name(name).build();
        //添加供应商节点
        supplyGraphRepository.save(supplyGraph);
        CompanyGraph companyGraph = CompanyGraph.builder().name("baidu").fullName("百度科技").build();
        String indexName = companyGraph.getFullName() + "-" + supplyGraph.getName();
        //供应商关系
        SupplyRelationship supplyRelationship =
                SupplyRelationship.builder().company(companyGraph).supply(supplyGraph).amount(amount).scale(scale).indexName(indexName).build();
        //添加供应关系
        supplyRelationshipRepository.save(supplyRelationship);
    }

}
