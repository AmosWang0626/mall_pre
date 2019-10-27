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

### 单机配置多个host [mac篇]
- sudo vim /etc/hosts
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

## 三、安全认证

### maven dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
### application.yml config
```yaml
spring:
  security:
    user:
      name: user
      password: Hello666!
```
### defaultZone [server / consumer]
```yaml
eureka:
  client:
    service-url:
      defaultZone: http://user:Hello666!@localhost:8761/eureka/
```

## 四、启动后传统方式指定 profiles
```java
@EnableEurekaServer
@SpringBootApplication
public class ServerApplication {

    private static final Map<String, String> ENV_MAP = new TreeMap<>();

    static {
        ENV_MAP.put("s1", "slave1");
        ENV_MAP.put("s2", "slave2");
        ENV_MAP.put("s3", "slave3");
    }

    /**
     * 最大错误次数
     */
    private static final int ERROR_COUNT = 5;

    public static void main(String[] args) {
        int errorCount = 0;
        while (errorCount < ERROR_COUNT) {
            System.out.print("请选择运行环境" + ENV_MAP.keySet() + ": ");
            String profiles = new Scanner(System.in).nextLine();
            if (ENV_MAP.containsKey(profiles)) {
                profiles = ENV_MAP.get(profiles);
            } else {
                System.out.println("请输入正确的运行环境呦~~~");
                errorCount++;
                continue;
            }
            new SpringApplicationBuilder(ServerApplication.class).profiles(profiles).run(args);
        }
        System.exit(0);
    }
}
```
## 五、Docker 启动
- mvn package
- docker-compose up -d