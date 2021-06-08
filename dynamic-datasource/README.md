# 工程简介
     多数据源落地方案：dynamic-datasource
     


# 延伸阅读
        
        https://www.kancloud.cn/tracy5546/dynamic-datasource/2264611
        


# 多主多从                      纯粹多库（记得设置primary）                   混合配置
spring:                               spring:                               spring:
  datasource:                           datasource:                           datasource:
    dynamic:                              dynamic:                              dynamic:
      datasource:                           datasource:                           datasource:
        master_1:                             mysql:                                master:
        master_2:                             oracle:                               slave_1:
        slave_1:                              sqlserver:                            slave_2:
        slave_2:                              postgresql:                           oracle_1:
        slave_3:                              h2:                                   oracle_2:

================================>>>>

核心接口：
    BaseMapper<T>:基础的mapper操作 crud 分页等；
    IService<T>:业务层基础的操作；  默认实现ServiceImpl<T>
        save():
        saveBatch():
        remove():
        update():
        listMaps():
        listObjs():
        page():
     Wrapper<T>:包装器
        QueryWrapper：查询条件器
        LambdaUpdateWrapper：支持lambda表达式的更新
        UpdateWrapper:更新条件器
        LambdaQueryWrapper:支持lambda表达式的查询
