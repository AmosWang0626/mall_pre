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
