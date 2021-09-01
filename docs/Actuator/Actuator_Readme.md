# AppRun健康监控配置：Actuator

2021-08-04 15:14:04 周三

参考：

- [Spring Boot Actuator Web API Documentation](https://docs.spring.io/spring-boot/docs/2.5.3/actuator-api/htmlsingle/)

## 问题

### 404问题、Web端+JVMX端访问配置

[Spring Boot 请求/actuator/beans 无法访问 返回404](https://blog.csdn.net/qq_36406189/article/details/103062154)

### spring-actuator /actuator","templated":false

- [Spring Boot Actuator:健康检查、审计、统计和监控](https://blog.csdn.net/sinat_23324343/article/details/89187763)
- [spring boot actuator监控详细介绍一(超级详细)](https://blog.csdn.net/weixin_43353498/article/details/89226101?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.base&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.base)

## 主要路径/接口

主入口：

```http request
http://localhost:8088/actuator
```
 
结果：

```json
{
  "_links": {
    "self": {
      "href": "http://localhost:8088/actuator",
      "templated": false
    },
    "beans": {
      "href": "http://localhost:8088/actuator/beans",
      "templated": false
    },
    "caches-cache": {
      "href": "http://localhost:8088/actuator/caches/{cache}",
      "templated": true
    },
    "caches": {
      "href": "http://localhost:8088/actuator/caches",
      "templated": false
    },
    "health": {
      "href": "http://localhost:8088/actuator/health",
      "templated": false
    },
    "health-path": {
      "href": "http://localhost:8088/actuator/health/{*path}",
      "templated": true
    },
    "info": {
      "href": "http://localhost:8088/actuator/info",
      "templated": false
    },
    "conditions": {
      "href": "http://localhost:8088/actuator/conditions",
      "templated": false
    },
    "configprops": {
      "href": "http://localhost:8088/actuator/configprops",
      "templated": false
    },
    "configprops-prefix": {
      "href": "http://localhost:8088/actuator/configprops/{prefix}",
      "templated": true
    },
    "env": {
      "href": "http://localhost:8088/actuator/env",
      "templated": false
    },
    "env-toMatch": {
      "href": "http://localhost:8088/actuator/env/{toMatch}",
      "templated": true
    },
    "loggers": {
      "href": "http://localhost:8088/actuator/loggers",
      "templated": false
    },
    "loggers-name": {
      "href": "http://localhost:8088/actuator/loggers/{name}",
      "templated": true
    },
    "heapdump": {
      "href": "http://localhost:8088/actuator/heapdump",
      "templated": false
    },
    "threaddump": {
      "href": "http://localhost:8088/actuator/threaddump",
      "templated": false
    },
    "metrics-requiredMetricName": {
      "href": "http://localhost:8088/actuator/metrics/{requiredMetricName}",
      "templated": true
    },
    "metrics": {
      "href": "http://localhost:8088/actuator/metrics",
      "templated": false
    },
    "scheduledtasks": {
      "href": "http://localhost:8088/actuator/scheduledtasks",
      "templated": false
    },
    "mappings": {
      "href": "http://localhost:8088/actuator/mappings",
      "templated": false
    }
  }
}
```