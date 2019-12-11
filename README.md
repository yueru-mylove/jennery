# k8s 单机部署指南 -非tls bootstrap部署

> 因为目前只有两台服务器，之前已经部署过一次tls bootstrap形式的cluster。后因为ssh账户被攻破而重置了ECS。而且考虑到现在主要是为了使用k8s部署应用，并且ECS资源的限制。因此就不打算再一次部署cluster。这里只做单机非安全模式的部署。

### kubelet 部署过程中的问题

> kubelet是一个特殊的组件。需要使用context文件。创建context文件的步骤如下：

```shell script
kc config set-cluster kubernetes --server=http://127.0.0.1:8080

kc config set-context default --cluster kubernetes

kc config use-context default
```

- kubelet config配置文件如下
```
KUBELET_ADDRESS="--address=0.0.0.0"
KUBELET_POD_INFRA_CONTAINER="--pod-infra-container-image=registry.access.redhat.com/rhel7/pod-infrastructure"
KUBELET_ARGS="--enable-server=true --enable-debugging-handlers=true --fail-swap-on=false --kubeconfig=/etc/kubernetes/config --cgroup-driver=systemd"
KUBE_LOGTOSTDERR="--logtostderr=false"
KUBE_LOGDIR="--log-dir=/var/log/kubenetes"
KUBE_LOG_LEVEL="--v=2"
KUBE_CGROUPS="--runtime-cgroups=/systemd/system.slice --kubelet-cgroups=/systemd/system.slice"
KUBE_HOSTNAME="--hostname-override=localhost"
```

- systemctl 启动文件
```
[Unit]
Description=Kubernetes Kubelete Server
After=docker.service
Requires=docker.service

[Service]
WorkingDirectory=/var/lib/kubelet
EnvironmentFile=/etc/kubernetes/kubelet
ExecStart=/usr/bin/kubelet \
                      $KUBELET_ADDRESS            \
                      $KUBELET_POD_INFRA_CONTAINER  \
                      $KUBELET_ARGS     \
                      $KUBE_LOGTOSTDERR \
                      $KUBE_LOGDIR \
                      $KUBE_LOG_LEVEL \
                      $KUBE_CGROUPS \
                      $KUBE_HOSTNAME

Restart=on-failure

[Install]
WantedBy=multi-user.target
```
> 注意，因为我们的kubelet config文件中的 /etc/kubernetes/config文件其实是上面使用kubectl生成的context文件。此时需要我们使用
```shell script
kc config view
```
> 来获取到context文件的内容并将其放置在/etc/kubernetes/config下，然后使用systemctl启动即可。