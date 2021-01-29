package com.demo.springboot.consulesweb.repository.jpa;

import com.demo.springboot.consulesweb.entity.jpa.ProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 产品 jpa仓储接口
 * @date 2021/1/29 10:05
 * @see
 */
@Repository
public interface ProductJpaRepository extends JpaRepository<ProductJpa, Long>, JpaSpecificationExecutor<ProductJpa> {
    // PagingAndSortingRepository: crud + 排序+分页
    // JpaSpecificationExecutor:jpa Criteria 查询，需调用方 具体实现；
    // 自定义接口；
    // 多条件查询：需符合JPA中对仓储接口的限制规范（按照接口方法名，自动生成sql）
    // 命名查询：@NamedQuery 标记在 仓储实体类上，而不是仓储接口上；
    // 自定义查询：@Query


    /**
     * 命名参数
     * 描述：推荐使用这种方法，可以不用管参数的位置
     */
    @Query("select u from ProductJpa u where u.productname = :name")
    ProductJpa findDataByName(@Param("name") String productName);

    /**
     * 索引参数
     * 描述：使用?占位符
     * 1表示第一个参数
     */
    @Query("select u from ProductJpa u where u.id = ?1")
    ProductJpa findDataById(long id);

    /**
     * 描述：可以通过@Modifying和@Query来实现更新
     * 注意：Modifying queries的返回值只能为void或者是int/Integer
     */
    @Modifying
    @Query("update ProductJpa p set p.productname = :name where p.id = :id")
    int updateDataById(@Param("name") String productName, @Param("id") int id);

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 命名查询
     * @date 2021/1/29 10:32
     */
    List<ProductJpa> findWithProductName(@Param("productName") String productName);
    /**
     * 说明：按照Spring data 定义的规则，查询方法以find|read|get开头
     * 涉及条件查询时，条件的属性用条件关键字连接，要注意的是：条件属性首字母需大写
     */

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from ProductJpa u where u.productName = :productName and u.productType = :productType
     * 参数名大写，条件名首字母大写，并且接口名中参数出现的顺序必须和参数列表中的参数顺序一致
     */
    ProductJpa findByProductnameAndProducttype(String productname, String producttype);

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from User u where u.name = ?1 or u.password = ?2
     */
    List<ProductJpa> findByProductnameOrProducttype(String productname, String producttype);

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from User u where u.id between ?1 and ?2
     */
    List<ProductJpa> findByIdBetween(Integer start, Integer end);

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from User u where u.id < ?1
     */
    List<ProductJpa> findByIdLessThan(Integer end);

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from User u where u.id > ?1
     */
    List<ProductJpa> findByIdGreaterThan(Integer start);

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from User u where u.name is null
     */
    List<ProductJpa> findByProductnameIsNull();

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from User u where u.name is not null
     */
    List<ProductJpa> findByProductnameIsNotNull();

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from User u where u.name like ?1
     */
    List<ProductJpa> findByProductnameLike(String productName);

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from User u where u.name not like ?1
     */
    List<ProductJpa> findByProductnameNotLike(String productName);

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from User u where u.password = ?1 order by u.id desc
     */
    List<ProductJpa> findByProductdescOrderByIdDesc(String productDesc);

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from User u where u.name <> ?1
     */
    List<ProductJpa> findByProductnameNot(String productName);

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from User u where u.id in ?1
     */
    List<ProductJpa> findByIdIn(List<Integer> ids);

    /**
     * 注：此处这个接口相当于发送了一条SQL:select u from User u where u.id not in ?1
     */
    List<ProductJpa> findByIdNotIn(List<Integer> ids);
}
