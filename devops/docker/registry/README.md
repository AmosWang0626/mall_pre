# 自建镜像仓库
> docker registry

## 启动仓库
- 拉取仓库镜像 `docker pull registry`

- 启动镜像仓库 `docker-compose up -d`

- 测试启动成功 `http://127.0.0.1:5000/v2/_catalog`

- 测试上传镜像
    - `docker pull busybox`
    - `docker tag busybox 127.0.0.1:5000/busybox`
    - `docker push 127.0.0.1:5000/busybox`

## push镜像报错了？
```text
The push refers to repository [127.0.0.1:5000/busybox]
Get https://127.0.0.1:5000/v2/: http: server gave HTTP response to HTTPS client
```

- 因为镜像仓库，默认要 https 才能上传

- 编辑 `vim /etc/docker/daemon.json`
- 增加 `insecure-registries`
```json
{
  "insecure-registries": ["127.0.0.1:5000"]
}
```

- 重启Docker服务 `systemctl restart docker`
