# 项目亮点

## 1、数据库层 JPA 自动建表优化
> 主要优化点为自动创建的表的字段顺序
1. 表字段顺序优化
    > 表中字段顺序 和 实体类中字段顺序一致
    - [自定义 PropertyContainer](https://github.com/AmosWang0626/mall/blob/master/mall-common/src/main/java/org/hibernate/cfg/PropertyContainer.java)

2. 实体类继承 BaseEntity 后表字段顺序优化
    > 实体类继承了父类，又不想父类字段都在表字段最前边。例如 id 放在第一位，createTime...deleteFlag 放最后
    - [自定义 InheritanceState](https://github.com/AmosWang0626/mall/blob/master/mall-common/src/main/java/org/hibernate/cfg/InheritanceState.java)

## 2、Nacos 集群部署
手册：[集群搭建手册](https://github.com/AmosWang0626/mall/blob/master/devops/nacos/nacos-cluster.md)

## 3、统一 Swagger 入口
> 所有的 Swagger 在网关模块暴露即可，其他服务不暴露 swagger-ui 页面。
- mall-gateway
    - com.mall.gateway.config.SwaggerConfig
    - com.mall.gateway.config.SwaggerProvider
    - com.mall.gateway.config.SwaggerResourceHandler

## 4、项目公用配置放在 Nacos 配置中心里边
> 例如一些数据库、Swagger、JPA等通用配置，每个服务配置一份很鸡肋。
>
> Nacos配置中心最佳实践，只需在 bootstrap.yml 里边配上 Nacos 配置中心地址即可。
