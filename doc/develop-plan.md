# develop plan
> 项目初衷是运用微服务相关框架，简单实现相关业务逻辑；
>
> 然后实践微服务部署，集群部署，K8S 部署一系列操作。

## 1、user（ing）
- 登录、注册、获取用户信息 [已完成]
- 濒临过期token刷新
- 请求发到各业务系统，各业务系统如何拿到用户ID [疑问点]
- 积分服务

## 2、order（尚未开始）
- 生成订单，查询订单

## 3、warehouse（尚未开始）
- 库存管理

## 4、gateway（ing）
- 网关层，WEB 请求入口
- 结合 Spring Cloud Gateway，配置相关路由实现 
- register, login 因涉及 jwt 相关的，此处暂通过 Feign 远程调用实现，参数校验使用了 Webflux 相关 API

### 4.1 gateway 模块兼容问题
- Spring Cloud Gateway 使用 netty 和 webflux 实现, 不兼容 spring-boot-starter-web, 故需要移去相关依赖
- Swagger 适用于 spring-boot-starter-web, 故暂时去掉 Swagger, 待兼容 Webflux 时再引入