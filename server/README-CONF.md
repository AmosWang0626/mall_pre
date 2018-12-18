## 配置与说明

## 一、单机配置

### 配置文件内容
```yaml
server:
  port: 8761
spring:
  application:
    name: mall-server

# eureka 服务注册中心配置
eureka:
  instance:
    hostname: localhost

  client:
    # 代表不向注册中心注册自己 [集群配置时请去掉]
    register-with-eureka: false
    # 维护服务不需要检索服务 [集群配置时请去掉]
    fetch-registry: false
    # service-url:
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka
```

## 二、集群配置

### 单机配置多个host [windows篇]
- 路径：C:\Windows\System32\drivers\etc
- 修改hosts文件：127.0.0.1 slave1 slave2 slave3

### 项目打包与启动
- 生成 server.jar
  - mvn package
- 启动 server.jar
  - java -jar server.jar
- 请选择运行环境[s1, s2, s3]:
  - s1 或者 s2 或者 s3

### 配置文件内容
```yaml
spring:
  application:
    name: mall-server

eureka:
  client:
    service-url:
      defaultZone: http://slave1:8761/eureka,http://slave2:8762/eureka,http://slave3:8763/eureka

info:
  app:
    name: mall-server
    description: Eureka 注册中心
    version: 1.0.0

---
spring:
  profiles:
    slave1
server:
  port: 8761
eureka:
  instance:
    hostname: slave1

---
spring:
  profiles:
    slave2
server:
  port: 8762
eureka:
  instance:
    hostname: slave2

---
spring:
  profiles:
    slave3
server:
  port: 8763
eureka:
  instance:
    hostname: slave3
```