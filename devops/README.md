# 前世今生
> 写个长文吧 2020-09-07 21:23

18年12月伊始，现在已经20年9月了，它要是个项目的话，我注定是不负责任的，所以它至今仍算个demo，
核心功能没实现，架构倒是换了一套又一套，总在重构，总在优化，我也是想借此圆了我微服务的梦。

至今不知道，什么时候，我能真正地在公司运用微服务，什么时候我能成为架构师，对技术游刃有余，不过
终究是近了，说起架构师，18年7月毕业，现在也两年有余了。从实习时的第一个项目开始，优化这个词始终
伴随着我，当然读者估计也是，优化、重构当然是为了项目更好地开发、维护，看人月神话，知道项目有个熵，
在开发阶段，软件的熵是不断缩小的，项目逐步从非稳态到亚稳态；但是在维护阶段，这个熵是不断增加的，
项目就会退化到非稳态。

开发中，所以我在开发中就严格要求自己，永远不要把优化留到明天，当然明天是泛指。

## 项目的架构
mall，顾名思义，电商平台，这也是我最容易想到的微服务应用场景。

一个网关，流量的入口，当然也是熔断、限流的切入点。用户、订单、仓储，可能是不太成熟的想法，将就下。

微服务的中间件有哪些呢？注册中心、配置中心、RPC框架、MQ、定时任务、网关、日志、分布式事务。。。

做大就多了，可是不往大了做，怎么知道技术合不合适呢？任何一个中间件都有很多实现。

第一版：eureka、feign、ribbon、docker，后来eureka新版不维护了，就放弃了，说实话，eureka应该是最简单
的注册中心；

第二版：nacos、gateway + webflux + swagger、sentinel、kafka、redisson，值得一提的是gateway、
sentinel，感觉网关和限流很实用

第三版：dubbo、rabbitmq，目前在做的，后边整合进来Seata、Apollo、Prometheus、Cat、ELK之类的。

使用 Rabbit MQ，主要是 kafka环境搭着麻烦、并没有考虑他们的性能，各有所长吧。

使用 Dubbo，主要是考虑到 Dubbo RPC 比 Feign HTTP要快一些，RPC是微服务的标配吧，现在结合 Nacos
门槛也降低了许多。当然，RPC和 HTTP并不是对立面，RPC远程服务调用，HTTP不也是吗？只不过 Http请求的
封装、解析复杂一些罢了，此时可能需要的是一个轻量级的协议。


## 环境搭建
1. Nacos
    > 阿里云服务器，学生机的话建议关掉不必要启动的应用，给Nacos多留点内存，不然Nacos容易挂。
2. MySQL
    > 树莓派Docker
3. Redis
    > 树莓派Docker
    >
    > `docker run -d -p 6379:6379 --name redis redis`
4. Rabbit MQ
    > 树莓派Docker
    >
    > `docker run -d -p 5672:5672 -p 15672:15672 --name rabbitmq rabbitmq:3.8.7-management-alpine`

不要问，为什么不用树莓派跑 Nacos？
树莓派是 ARM 架构，跑 Nacos 还是比较吃力的，和 X86 架构差的远。

小插曲：
```text
用 VMWare搭了几台 CentOS集群，都安装上 Nacos，集群联调也正常；就想着把数据库、Redis、Rabbit MQ 都装虚拟机上吧。
就顺便在虚拟机上搭了 Docker环境，docker-compose启动了 Rabbit MQ，具体配置看devops/docker/rabbitmq

然后，项目就起不来了，启动后 Dubbo绑定的20880端口绑定在虚拟机上、或者Docker所在网络上、就是不能绑到真正主机ip上。
网上也有说设置dubbo.protocol.host，但是dubbo绑定的host绑错了，nacos去对的上边找服务也找不到。

想着用了6年的电脑，还是留点性能给 IDEA 吧，树莓派（吃灰派）是时候展示作用了。
```
