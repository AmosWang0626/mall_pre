# Swagger
> Swagger 3.X 增加支持WebFlux，同时WebMvc也有相应调整
>
> 截至2020年2月11日 swagger 3.X 尚未正式发布，项目需要，先用快照版

## 相关源码
- WebMvc，可参考 mall-user 模块
- WebFlux，可参考 mall-gateway 模块

## 一、Swagger 3.X 相关 jar 引入项目
1. maven 增加仓库配置
    - windows `C:\Users\User\.m2\setting.xml`
    - 添加如下内容

```xml
<mirror>
    <id>oss-snapshot</id>
    <name>OSS Snapshot</name>
    <url>http://oss.jfrog.org/oss-snapshot-local</url>
    <mirrorOf>*</mirrorOf>    
</mirror>
```

2. pom 里添加仓库配置

```xml
<repository>
   <id>oss-snapshot</id>
   <name>OSS Snapshot</name>
   <url>http://oss.jfrog.org/oss-snapshot-local</url>
   <snapshots>
       <enabled>true</enabled>
   </snapshots>
</repository>
```

## 二、WebMvc

```xml
<dependencies>
    <dependency>
         <groupId>io.springfox</groupId>
         <artifactId>springfox-swagger-ui</artifactId>
         <version>3.0.0-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>3.0.0-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-spring-webmvc</artifactId>
        <version>3.0.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```

## 三、WebFlux

```xml
<dependencies>
    <dependency>
         <groupId>io.springfox</groupId>
         <artifactId>springfox-swagger-ui</artifactId>
         <version>3.0.0-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>3.0.0-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-spring-webflux</artifactId>
        <version>3.0.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```
