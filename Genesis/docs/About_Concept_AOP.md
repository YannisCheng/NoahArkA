# 关于Spring的概念理解 — AOP

## 1. 基本概念


- **`IoC`（Inversion of Control）控制反转：**

使用 `IoC` 的目的：`降低耦合度`
`IoC(控制反转)` 理解：将`对象`的 **控制** **反转** 给`Spring`

- **AOP（Aspect Oriented Programming）面向切面编程**

- **底层原理：**

1. xml解析
2. 工厂模式
3.  反射

- **思想：**

`IoC思想`基于`IoC容器`，IoC容器 **底层** 就是 `工厂模式`

 [](/images/spring_ioc.png)

- **Spring中IoC容器实现的2种方式(接口)：**

1.  **BeanFactory**：最基本（内置）的实现，不提供给Dev人员使用。
2.  `加载配置文件`时使用`不创建对象`，仅在 `获取(使用)对象` 时才创建对象。
3.  **ApplicationContext**：是 `BeanFactory` 的子实现接口，更强大，供dev人员使用。
    - 加载配置文件时就在配置文件中创建对象（Tomcat启动过程中就创建对象）。