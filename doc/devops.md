# 各服务默认配置

## 先决条件
- Nacos
- MySQL
- Redis
- RabbitMQ
- Sentinel

## 服务占用端口
| 服务类型 | 服务名称 | 占用端口 |
| --- | --- | --- |
| ops | mysql               |3306|
| ops | redis               |6379|
| ops | nacos               |8848|
| ops | sentinel            |8001|
| ops | sentinel dashboard  |8002|
| ops | rabbitmq            |5672|
| ops | rabbitmq management |15672|
| --- | --- | --- |
| dev | gateway             |8000|
| dev | user                |8010|
| dev | user-dubbo          |20810|
| dev | order               |8020|
| dev | order-dubbo         |20820|
| dev | warehouse           |8030|
| dev | warehouse-dubbo     |20830|


## gateway 网关服务
- 端口：8000
- application-name：mall-gateway
- 涉及技术：spring-cloud-gateway、dubbo、jwt、webflux、swagger、rabbit mq、sentinel
- 端口：8001 sentinel-server
- 端口：8002 sentinel-dashboard

## user 用户服务
- 端口：8010
- application-name：mall-user
- 涉及技术：dubbo、jpa、swagger、rabbit mq

## order 订单服务
- 端口：8020
- application-name：mall-order
- 涉及技术：暂无

## warehouse 仓储服务
- 端口：8030
- application-name：mall-warehouse
- 涉及技术：暂无
