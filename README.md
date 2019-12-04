
##### jennery-blog 
> 基于 SpringBoot 和SpringCloud 2.1.X 开发的简书型博客系统。


##### 使用指南
- jennery-register 注册中心和配置中心，使用k8s集群部署的consul集群。本地调试使用eureka和application.yml替代
- jennery-gateway   网关组件。使用Spring Cloud Gateway组件，配置网关路由和熔断（Hystrix or Resilience4j）。
- jennery-web       web端工程入口。 