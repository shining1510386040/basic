# 工程简介
    
    Springboot 整合es7 的版本，基于java rest high level client；
    注：spring-data 的方式，版本较官网落后；
    


# 延伸阅读
    参考：
    https://www.jianshu.com/p/ec6e57ad1cde
## es中文档的结构？
Index库->Document文档

文档元数据（Document）

用于标注文档的相关信息
_index ：文档所属的索引名
_type ：文档所属的类型名
_id ：文档唯一ID
_source：文档的原始 JSON 数据
_version：文档的版本信息
_score：相关性打分
    
## 关于es中type？
    
    Elasticsearch：index --> type --> doc --> field
    
    MySQL: 数据库 --> 数据表 --> 行 --> 列
    
    因为关系型数据库比非关系型数据库的概念提出的早，而且很成熟，应用广泛。
    
    所以，后来很多NoSQL（包括：MongoDB，Elasticsearch等）都参考并延用了传统关系型数据库的基本概念。
    
    一个客观的现象和事实如下：
    
    Elasticsearch 官网提出的近期版本对 type 概念的演变情况如下：
    
    在 5.X 版本中，一个 index 下可以创建多个 type；
    
    在 6.X 版本中，一个 index 下只能存在一个 type；
    
    在 7.X 版本中，直接去除了 type 的概念，就是说 index 不再会有 type。