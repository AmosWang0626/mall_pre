# Nacos集群搭建

## 步骤列表：
1. MySQL环境准备
2. 集群服务规划
3. 配置Nginx同一入口
4. 遇到的问题


## 1、MySQL环境准备
- 1.1 创建数据库、相关表
> 创建数据库 `nacos_config`
>
> 生成相关表 `nacos_home/conf/nacos-mysql.sql`

- 1.2 创建 MySQL 用户
```sql
CREATE USER 'u_nacos'@'%' IDENTIFIED WITH mysql_native_password BY '@Nacos2020';
GRANT ALL PRIVILEGES ON nacos_config.* TO 'u_nacos'@'%';
```

- 1.3 修改配置文件增加如下内容 `nacos_home/conf/application.properties`
```properties
spring.datasource.platform=mysql

# replicas count
db.num=2

db.url.0=jdbc:mysql://11.162.196.16:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.url.1=jdbc:mysql://11.163.152.9:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true

db.user=u_nacos
db.password=@Nacos2020
```

- 1.4 支持 MySQL8.0 以上版本
> 需要特殊处理下，上传到指定目录 `/nacos_home/plugins/mysql/`
>
>[mysql-connector-java-8.0.16.jar](https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.16/mysql-connector-java-8.0.16.jar)


## 2、集群服务规划

| 节点 | 端口 |
| --- | --- |
| 127.0.0.1 | 8050 |
| 127.0.0.1 | 8051 | 
| 127.0.0.1 | 8052 | 

- 2.1 复制三份 Nacos

- 2.2 修改配置文件：`nacos_home/conf/application.properties` 
> 分别设置：`server.port=8050`

- 2.3 修改配置文件`nacos_home/conf/cluster.conf`
```
# ip:port
127.0.0.1:8848
127.0.0.1:8849
127.0.0.1:8850
```

- 2.4 如果服务器配置不是很高
> 修改下启动配置 `nacos_home/bin/startup.sh`
```jvm
# JAVA_OPT="${JAVA_OPT} -server -Xms2g -Xmx2g -Xmn1g -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"
JAVA_OPT="${JAVA_OPT} -server -Xms512m -Xmx512m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m"
```

## 3、配置Nginx同一入口
```text
upstream nacos-server {
  server 127.0.0.1:8848;
  server 127.0.0.1:8849;
  server 127.0.0.1:8850;
}

server {
  listen 80;
  server_name  localhost;
  location /nacos/ {
    proxy_pass http://nacos-server/nacos/;
  }
}
```

## 4、遇到的问题
> 服务启动好好地，不知不觉就挂掉了，一定要检查下服务器内存之类的（谨防 OOM）。
