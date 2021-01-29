package com.demo.springboot.consulesweb.controller;

import com.demo.springboot.consulesweb.entity.jdbc.ProductJdbc;
import com.demo.springboot.consulesweb.repository.jdbc.ProductJdbcRepository;
import com.demo.springboot.consulesweb.vo.PageResult;
import com.demo.springboot.consulesweb.vo.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Jdbc 仓储测试controller
 * @date 2021/1/26 19:22
 * @see
 */
@RestController
@RequestMapping("/jdbc/product")
public class ProductJdbcController {

//    @Autowired
//    private ProductJdbcRepository productJdbcRepository;

    /**
     * spring boot 自动配置的jdbctemplate模板
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    /**
//     * @param
//     * @return
//     * @author Wenyi Cao
//     * @version 1.0
//     * @description 查询单个
//     * @date 2021/1/26 19:27
//     */
//    @GetMapping("/getOne/{id}")
//    public ProductJdbc getOne(@PathVariable long id) {
//
//        Optional<ProductJdbc> ret = productJdbcRepository.findById(id);
//        return ret.get();
//    }
//
//    /**
//     * @param
//     * @return
//     * @author Wenyi Cao
//     * @version 1.0
//     * @description 保存
//     * @date 2021/1/26 19:26
//     */
//    @PostMapping("/save")
//    public ProductJdbc save(ProductJdbc data) {
//        ProductJdbc save = productJdbcRepository.save(data);
//        return save;
//    }
//
//    /**
//     * @param
//     * @return
//     * @author Wenyi Cao
//     * @version 1.0
//     * @description 查询所有
//     * @date 2021/1/26 19:28
//     */
//    @PostMapping("/list")
//    public List<ProductJdbc> findAll() {
//
//        Iterable<ProductJdbc> all = productJdbcRepository.findAll();
//        // iterable 转list
//        List ret = new ArrayList(16);
//        // 循环处理
//        all.forEach(item -> {
//            ret.add(item);
//        });
//        return ret;
//    }
//
//
//    /**
//     * @param
//     * @return
//     * @author Wenyi Cao
//     * @version 1.0
//     * @description 删除
//     * @date 2021/1/29 11:07
//     */
//    @PostMapping("/delete")
//    public void delete(@RequestParam long id) {
//        productJdbcRepository.deleteById(id);
//    }
//
//    /**
//     * @param
//     * @return
//     * @author Wenyi Cao
//     * @version 1.0
//     * @description 分页查询
//     * @date 2021/1/29 11:10
//     */
//    @PostMapping("/page")
//    public ServiceResult findPage(ProductJdbc search, @RequestParam int pageNo, @RequestParam int pageSize) {
//
//        // .分页条件,排序条件（id降序）
//        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(search.getId() + "").descending());
//        Page<ProductJdbc> retPage = productJdbcRepository.findAll(page);
//        // 数据
//        List<ProductJdbc> data = retPage.getContent();
//        // 总条数
//        long totalElements = retPage.getTotalElements();
//        PageResult pageResult = new PageResult();
//        pageResult.setCurrentPageNo(pageNo);
//        pageResult.setCurrentPageSize(pageSize);
//        pageResult.setTotal(totalElements);
//        pageResult.setData(data);
//
//        ServiceResult ret = new ServiceResult("200", "操作成功！", pageResult);
//        return ret;
//
//    }

    // =======================基于jdbc template的操作

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 多条件查询
     * @date 2021/1/29 12:57
     */
    @PostMapping("/jdbctemplate/search")
    public List<ProductJdbc> getAll(ProductJdbc search) {
        String sql = "select * from product_jdbc where 1=1 and product_name = '" + search.getProductName() + "'";
        List<ProductJdbc> productJdbcList = jdbcTemplate.queryForList(sql, ProductJdbc.class);
        return productJdbcList;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查询
     * @date 2021/1/29 12:57
     */
    @PostMapping("/jdbctemplate/query")
    public List<ProductJdbc> getList(ProductJdbc search) {
        String sql = "select * from product_jdbc where 1=1 and product_name = ? and product_type like ?";
        Object[] params = new Object[2];
        params[0] = search.getProductName();
        params[1] = search.getProductType();
        // 有sql入参；查询多个
        List<ProductJdbc> ret = jdbcTemplate.queryForList(sql, ProductJdbc.class, params);
//        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, params);
        // 有sql入参；查询单个
//        jdbcTemplate.queryForObject(sql,params,ProductJdbc.class);
//        jdbcTemplate.queryForObject(sql,params,new BeanPropertyRowMapper<>(ProductJdbc.class));
        return ret;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 更新
     * @date 2021/1/29 12:57
     */
    @PostMapping("/jdbctemplate/update")
    public int updateData(ProductJdbc data) {
        String sql = "update product_jdbc set product_name = ?1 , product_type = ?2";
        Object[] params = new Object[2];
        params[0] = data.getProductName();
        params[1] = data.getProductType();
        int update = jdbcTemplate.update(sql, params);
        return update;
    }


}
