# gateway
> 网关系统【Spring Cloud应用场景丰富】
>
> 页面中的链接直达官方wiki

## 1.Actuator
> 服务监控与管理
- 通过 logger post 在运行时改变日志等级,常用于线上排错
> `curl -X POST "http://localhost:8000/actuator/loggers/root" -H "Content-Type: application/json" -d "{ \"configuredLevel\": \"DEBUG\"}"`

## 2.OpenFeign
> 声明式伪HTTP客户端

## 3.Ribbon 
> 负载均衡 @LoadBalanced

## 4.Sentinel哨兵
> [Sentinel](https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel) 以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

### 4.1 [流量控制](https://github.com/alibaba/Sentinel/wiki/%E6%B5%81%E9%87%8F%E6%8E%A7%E5%88%B6)
> 流量控制（flow control），其原理是监控应用流量的 QPS 或并发线程数等指标，当达到指定的阈值时对流量进行控制，以避免被瞬时的流量高峰冲垮，从而保障应用的高可用性。
>
> 相关常量参考这个类：com.alibaba.csp.sentinel.slots.block.RuleConstant
- `resource`：资源名，即限流规则的作用对象
    - @SentinelResource("`resource 对应的 value`")
- `count`: 限流阈值
- `grade`: 限流阈值类型
    - 并发线程数 `0` 
    - QPS `1`
- `limitApp`: 流控针对的调用来源
    > `strategy` = `RuleConstant.STRATEGY_DIRECT` 时生效
    - `default` 不区分调用来源
    - `xxx` 指定的 `origin_name`
    - `other` 非指定的 `origin_name`
- `strategy`: 调用关系限流策略

    |描述|值|常量|
    |---|---|---|
    |根据调用方限流-直接关联|`0`|RuleConstant.STRATEGY_DIRECT|
    |具有关系的资源流量控制-关联流量控制|`1`|RuleConstant.STRATEGY_RELATE|
    |根据调用链路入口限流-链路限流|`2`|RuleConstant.STRATEGY_CHAIN|

- `controlBehavior`: 流量控制效果

    |描述|值|常量|
    |---|---|---|
    |直接拒绝|`0`|RuleConstant.CONTROL_BEHAVIOR_DEFAULT|
    |预热启动|`1`|RuleConstant.CONTROL_BEHAVIOR_WARM_UP|
    |匀速排队|`2`|RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER|
    |预热启动+匀速排队|`3`|RuleConstant.CONTROL_BEHAVIOR_WARM_UP_RATE_LIMITER|

#### 示意图如下

- 预热启动
> 当系统长期处于低水位的情况下，当流量突然增加时，直接把系统拉升到高水位可能瞬间把系统压垮。通过"冷启动"，让通过的流量缓慢增加，在一定时间内逐渐增加到阈值上限，给冷系统一个预热的时间，避免冷系统被压垮。

![示意图](https://user-images.githubusercontent.com/9434884/68292392-b5b0aa00-00c6-11ea-86e1-ecacff8aab51.png)

- 匀速排队
> 严格控制请求通过的间隔时间，也即是让请求以均匀的速度通过，对应的是漏桶算法。

![示意图](https://user-images.githubusercontent.com/9434884/68292442-d4af3c00-00c6-11ea-8251-d0977366d9b4.png)

---

### 4.2 降级

### 4.3 动态数据源
> Nacos为例
#### 4.3.1 项目中配置
```yaml
spring:
  cloud:
    sentinel:
      datasource:
        ds:
          nacos:
            server-addr: amos.wang:8088
            data-id: ${spring.application.name}-sentinel
            group-id: DEFAULT_GROUP # default DEFAULT_GROUP
            data-type: json # default json
            rule-type: flow
```
#### 4.3.2 Nacos控制台增加配置
- Data ID: mall-gateway-sentinel
- Group: DEFAULT_GROUP
- 配置内容
```json
[
    {
        "resource": "/sentinel/hello",
        "limitApp": "default",
        "grade": 1,
        "count": 1,
        "strategy": 0,
        "controlBehavior": 0,
        "clusterMode": false
    }
]
```

### 4.4 Sentinel控制台
> [sentinel-dashboard](https://github.com/alibaba/Sentinel/tree/master/sentinel-dashboard)
#### 参数配置
- 指定接入Sentinel控制台的地址 `Dcsp.sentinel.dashboard.server=localhost:8080`	
- 指定Sentinel应用名称 `-Dproject.name=sentinel-dashboard`
- 控制台登录用户名 `-Dsentinel.dashboard.auth.username=sentinel` default: sentinel
- 控制台登录密码 `-Dsentinel.dashboard.auth.password=000000` default: sentinel    
- 服务端session的过期时间 `-Dserver.servlet.session.timeout=60m` default: 30m
    - 7200 表示 7200 秒；60m 表示 60 分钟

#### 控制台启动
- 不同系统命令换行符：`Linux: \ Windows: ^`
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
