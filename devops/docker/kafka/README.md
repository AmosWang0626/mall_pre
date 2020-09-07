# start
`docker-compose up -d`
`docker-compose start`

# 暂停 恢复
`docker-compose pause`
`docker-compose unpause`

# ps
> 比`docker ps`看着顺眼多了
`docker-compose ps`

# stop and rm
- 之前可能会使用这种 `docker stop amos-kafka zk1 zk2 zk3 & docker rm amos-kafka zk1 zk2 zk3`
- 现在可以这样 `docker-compose stop & docker-compose rm`
- 还有更简单的？`docker-compose down`

# restart
`docker-compose restart`

# logs
`docker-compose logs -f`
`docker-compose events --json`

# 验证 `docker-compose` 配置
`docker-compose config -q`
  - 正确时: 没输出
  - 错误时: 输出错误

# build 镜像
`docker-compose build`

`docker-compose build --no-cache`

# run
> 没亲自用过
`docker-compose run`

# Kafka
> 参考文档 --- [Kafka 官方中文文档](http://kafka.apachecn.org/quickstart.html)
>
> 参考文档 --- [wurstmeister/kafka-docker 文档](http://wurstmeister.github.io/kafka-docker/)

## 环境搭建
> Docker 那么香，何乐而不为~~~
- Docker + Kafka + Zookeeper
    - [docker-compose.yml](https://github.com/AmosWang0626/mall/blob/master/devops/kafka/docker-compose.yml)
    - 使用的镜像
        - wurstmeister/kafka
        - zookeeper

### 待启动完成后
1. 进入启动后的Kafka容器
    - `docker exec -it amos-kafka /bin/bash`
2. 创建 Topic
    - `kafka-topics.sh --create --zookeeper zoo1:2181 --replication-factor 1 --partitions 1 --topic test`
3. 查看创建的 Topic
    - `kafka-topics.sh --list --zookeeper zoo1:2181`
4. 创建生产者
    - `kafka-console-producer.sh --broker-list localhost:9092 --topic test`
5. 创建消费者
    - `kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test --from-beginning`

![示意图](https://gitee.com/AmosWang/resource/raw/master/image/kafka-zk-topic-test.png)

### 拓展知识
1. Docker 进入容器后，看一下 Kafka 的命令能不能直接使用
    - `kafka-topics.sh --help`
        - 结果：可以直接使用
    - 拓展1 --- which 查找命令 `which kafka-topics.sh`
    - 拓展2 --- find 查找文件 `find / -name kafka-topics.sh`

2. 删除 Topic
    - `kafka-topics.sh --delete --zookeeper zoo1:2181 --topic test_topic`
    - 默认情况下，Kafka 的删除为逻辑删除（marked for deletion）
        - Kafka 物理删除配置 `server.properties` 增加 `delete.topic.enable=true`
    - 逻辑删除的内容，想要物理删除，需要在对应 zookeeper 上操作
        - 进入 Topic 所在 ZK 容器 `docker exec -it zk1 /bin/bash`
        - 进入 ZK 客户端 `zkCli.sh`
        - 找到对应 Topic `ls /brokers/topics`
        - 删除想删的 Topic `deleteall /brokers/topics/test` 
        - 注: rmr 已过时，可用 deleteall 代替。
            - The command 'rmr' has been deprecated. Please use 'deleteall' instead.

## Kafka 基本概念
> 示例配置如下

### 配置方式
> 注意：bindings 节点在 stream 下边
```yaml
spring:
  cloud:
    stream:
      bindings: # 服务的整合处理
        order:  # 通道的名称
          destination: order_create # Kafka中的 topic RabbitMQ中的 exchange
          content-type: application/json # 设置消息类型，文本可设置为 text/plain
          group: order_notice # 分组（防止重复消费，并且会持久化消息）
```

### 错误示例
> 特别注意：这种配置方式 group 不生效，想来原因也很简单，bindings配置在 kafka 下边，kafka下边应该是一些 kafka 独有的配置
```yaml
spring:
  cloud:
    stream:
      kafka:
        bindings: # 服务的整合处理
          order:  # 通道的名称
            destination: order_create # Kafka中的 topic RabbitMQ中的 exchange
            content-type: application/json # 设置消息类型，文本可设置为 text/plain
            binder: xxx # 默认绑定器
            group: order_notice # 分组（据说 group 不能带横杠 -）
```