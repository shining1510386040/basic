# 工程简介
      spring boot 对web的支持  与 mybatis的整合 


# 延伸阅读
    问题1：spring boot maven 插件打包后找不到主类？
    
    解决：https://blog.csdn.net/pengjunlee/article/details/90639129
    
    执行打包：mvn package
             spring-boot:repackage
             
    问题2：docker 插件？
    第三方插件：有spotify和fabric8出品的两种docker-maven-plugin
    配置：
    <build>
        <plugins>
            <plugin>
                <groupId>io.fabric8</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>0.33.0</version>
                <configuration>
                    <!-- Docker 远程管理地址-->
                    <dockerHost>http://192.168.3.101:2375</dockerHost>
                    <!-- Docker 推送镜像仓库地址-->
                    <pushRegistry>http://192.168.3.101:5000</pushRegistry>
                    <images>
                        <image>
                            <!--由于推送到私有镜像仓库，镜像名需要添加仓库地址-->
                            <name>192.168.3.101:5000/mall-tiny/${project.name}:${project.version}</name>
                            <!--定义镜像构建行为-->
                            <build>
                                <!--定义基础镜像-->
                                <from>java:8</from>
                                <args>
                                    <JAR_FILE>${project.build.finalName}.jar</JAR_FILE>
                                </args>
                                <!--定义哪些文件拷贝到容器中-->
                                <assembly>
                                    <!--定义拷贝到容器的目录-->
                                    <targetDir>/</targetDir>
                                    <!--只拷贝生成的jar包-->
                                    <descriptorRef>artifact</descriptorRef>
                                </assembly>
                                <!--定义容器启动命令-->
                                <entryPoint>["java", "-jar","/${project.build.finalName}.jar"]</entryPoint>
                                <!--定义维护者-->
                                <maintainer>macrozheng</maintainer>
                            </build>
                        </image>
                    </images>
                </configuration>
            </plugin>
        </plugins>
    </build>
    
    官方插件：springboot-maven-plugin
    其实对于SpringBoot应用来说，如何从应用Jar包构建Docker镜像，做法基本是差不多的,官方的把他省略了，可以不写dockerfile  或在插件中定义指定的构建步骤
    执行：mvn spring-boot：build-image
    配置：
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
        <configuration>
            <image>
                <!--配置镜像名称-->
                <name>192.168.3.101:5000/mall-tiny/${project.name}:${project.version}</name>
                <!--镜像打包完成后自动推送到镜像仓库-->
                <publish>true</publish>
            </image>
            <docker>
                <!--Docker远程管理地址-->
                <host>http://192.168.3.101:2375</host>
                <!--不使用TLS访问-->
                <tlsVerify>false</tlsVerify>
                <!--Docker推送镜像仓库配置-->
                <publishRegistry>
                    <!--推送镜像仓库用户名-->
                    <username>test</username>
                    <!--推送镜像仓库密码-->
                    <password>test</password>
                    <!--推送镜像仓库地址-->
                    <url>http://192.168.3.101:5000</url>
                </publishRegistry>
            </docker>
        </configuration>
    </plugin>       
    
   =======================>>>>
   如何自定义endpoint？
   
   解析：
   类上：
   @Endpoint 	该注解的类可以通过http查看也可以通过jmx查看，他是在两个地方注册 	相当于springmvc中的RestController和JMX中MBean的集合
   @JmxEndpoint 	该注解的类开放的是JMX接口 	相当于JMX中的MBean
   @WebEndpoint 	该注解的类开饭的是http接口 	相当于mvc当中的RestController
   方法上：
   @WriteOperation 	http-POST请求 	相当于mvc中的@PostMapping
   @ReadOperation 	http- GET请求 	相当于mvc中的@GetMapping
   @DeleteOpretation 	http- DELETE请求 	相当于mvc中的@DeleteMapping
   
   思路：
   1.写一个自定义的endpoint 类：
   2.@Bean的方式交给IOC容器
   
   测试：
   

    curl -X GET http://localhost:8080/actuator/endpointCustom?content=endpointGet

    你请求的内容: endpointGet

    curl -X POST http://localhost:8080/actuator/endpointCustom?content=endpointPost

    你写的内容: endpointPost

    curl -X DELETE http://localhost:8080/actuator/endpointCustom?content=endpointDELETE

    你删除的内容：endpointDELETE

==============================================》》》》
    如何自定义健康检查？
    
    解析：
    HealthEndPoint：
       接口：HealthIndicator
       实现类：
        xxxHealthIndicator
        例如：DataSourceHealthIndicator：db
              RedisHealthIndicator：redis
              MongoHealthIndicator：mongo
              DiskSpaceHealthIndicator:磁盘空间
              MailHealthIndicator：邮件
              ElasticsearchJestHealthIndicator：es
              。。。。
     思路：
     自定义一个xxxHealthIndicator @component 交给spring处理
       实现HealthIndicator接口或继承AbstractHealthIndicator抽象类