package com.demo.springboot.neo4j.repository;

import com.demo.springboot.neo4j.entity.SupplyRelationship;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 供应商关系仓储
 * @date 2021/5/21 11:01
 * @see
 */
@Repository
public interface SupplyRelationshipRepository extends Neo4jRepository<SupplyRelationship, Long> {
}
