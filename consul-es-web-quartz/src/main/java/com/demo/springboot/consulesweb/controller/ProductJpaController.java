package com.demo.springboot.consulesweb.controller;

import com.demo.springboot.consulesweb.entity.jpa.ProductJpa;
import com.demo.springboot.consulesweb.repository.jpa.ProductJpaRepository;
import com.demo.springboot.consulesweb.vo.PageResult;
import com.demo.springboot.consulesweb.vo.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description Jpa 仓储测试controller
 * @date 2021/1/26 19:22
 * @see
 */
@RestController
@RequestMapping("/jpa/product")
public class ProductJpaController {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查询单个
     * @date 2021/1/26 19:27
     */
    @GetMapping("/getOne/{id}")
    public ProductJpa getOne(@PathVariable long id) {

        ProductJpa one = productJpaRepository.getOne(id);
        return one;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 保存
     * @date 2021/1/26 19:26
     */
    @PostMapping("/save")
    public ProductJpa save(ProductJpa data) {
        ProductJpa save = productJpaRepository.save(data);
        return save;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 查询所有
     * @date 2021/1/26 19:28
     */
    @PostMapping("/list")
    public List<ProductJpa> findAll() {

        List<ProductJpa> all = productJpaRepository.findAll();
        return all;

    }


    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description JpaSpecificationExecutor 接口的CriteriaQuery查询
     * 按ids 查询
     * @date 2021/1/29 12:28
     */
    @GetMapping("/findByIds")
    public List<ProductJpa> findAllByIds(final List<Long> ids) {
//        List<ProductJpa> all = productJpaRepository.findAll(new Specification<ProductJpa>() {
//
//            @Override
//            public Predicate toPredicate(Root<ProductJpa> root,
//                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
//                return root.in(ids);
//            }
//        });
//        return all;

        return productJpaRepository.findAll((root, query, criteriaBuilder) -> {
//            query.orderBy()
            return root.in(ids);
        });
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 删除
     * @date 2021/1/29 11:07
     */
    @PostMapping("/delete")
    public void delete(@RequestParam long id) {
        productJpaRepository.deleteById(id);
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 分页查询
     * @date 2021/1/29 11:10
     */
    @PostMapping("/page")
    public ServiceResult findPage(ProductJpa search, @RequestParam int pageNo, @RequestParam int pageSize) {
        // 1.查询条件:严格匹配的，不老好使的，还不如不用jpa仓储进行查询呢，
        Example<ProductJpa> jpaExample = Example.of(search);
        // 2.分页条件,排序条件（id降序）
        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Order.desc("id")));
        Page<ProductJpa> retPage = productJpaRepository.findAll(jpaExample, page);
        // 数据
        List<ProductJpa> data = retPage.getContent();
        // 总条数
        long totalElements = retPage.getTotalElements();
        PageResult pageResult = new PageResult();
        pageResult.setCurrentPageNo(pageNo);
        pageResult.setCurrentPageSize(pageSize);
        pageResult.setTotal(totalElements);
        pageResult.setData(data);

        ServiceResult ret = new ServiceResult("200", "操作成功！", pageResult);
        return ret;

    }


}
