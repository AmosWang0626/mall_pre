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
- 修改hosts文件：127.0.0.1 slave1 slave2

### 项目打包与启动
- mvn package 生成 server.jar
- 启动 server.jar
- java -jar server.jar
- 请输入运行环境 (slave1 or slave2):
- slave1 或者 slave2

### 配置文件内容
```yaml
spring:
  application:
    name: mall-server
eureka:
  client:
    service-url:
      defaultZone: http://slave1:8761/eureka,http://slave2:8762/eureka

---
# 主机名 slave1
spring:
  profiles:
    slave1
server:
  port: 8761
eureka:
  instance:
    hostname: slave1

---
# 主机名 slave2
spring:
  profiles:
    slave2
server:
  port: 8762
eureka:
  instance:
    hostname: slave2
```