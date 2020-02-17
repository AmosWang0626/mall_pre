# gateway

## 网关系统

## Spring Cloud 相关组件
### 1. actuator 服务监控与管理
- 通过 logger post 在运行时改变日志等级,常用于线上排错
> `curl -X POST "http://localhost:8000/actuator/loggers/root" -H "Content-Type: application/json" -d "{ \"configuredLevel\": \"DEBUG\"}"`

### 2. openfeign 声明式伪HTTP客户端
### 3. Ribbon 负载均衡 @LoadBalanced
### 4. Sentinel 熔断器
- [sentinel-dashboard](https://github.com/alibaba/Sentinel/tree/master/sentinel-dashboard)
#### 参数说明
    - 指定接入Sentinel控制台的地址 `Dcsp.sentinel.dashboard.server=localhost:8080`	
    - 指定Sentinel应用名称 `-Dproject.name=sentinel-dashboard`
    - 控制台登录用户名 `-Dsentinel.dashboard.auth.username=sentinel` default: sentinel
    - 控制台登录密码 `-Dsentinel.dashboard.auth.password=000000` default: sentinel    
    - 服务端session的过期时间 `-Dserver.servlet.session.timeout=60m` default: 30m 
        - 7200 表示 7200 秒；60m 表示 60 分钟

#### 启动sentinel-dashboard
- 命令换行符：`Linux: \ Windows: ^`
- Linux
```shell script
java -Dserver.port=8002 \
-Dcsp.sentinel.dashboard.server=localhost:8002 \
-Dproject.name=sentinel-dashboard \
-Dsentinel.dashboard.auth.username=amos \
-Dsentinel.dashboard.auth.password=000000 \
-jar target/sentinel-dashboard.jar
```
- Windows
```shell script
java -Dserver.port=8002 ^
-Dcsp.sentinel.dashboard.server=localhost:8002 ^
-Dproject.name=sentinel-dashboard ^
-Dsentinel.dashboard.auth.username=amos ^
-Dsentinel.dashboard.auth.password=000000 ^
-jar target/sentinel-dashboard.jar
```
