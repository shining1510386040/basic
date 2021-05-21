package com.demo.springboot.neo4j.repository;

import com.demo.springboot.neo4j.entity.SupplyGraph;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 供应商仓储
 * @date 2021/5/21 10:59
 * @see
 */
@Repository
public interface SupplyGraphRepository extends Neo4jRepository<SupplyGraph, Long> {
}
