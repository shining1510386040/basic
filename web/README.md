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
    
    
             
    
