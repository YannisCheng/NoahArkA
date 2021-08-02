package com.cwj.scriptlib.autowired_mutil_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * com.cwj.scriptlib.autowired
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-02 19:50
 */
@Component
public class UseServiceDemo {


    /**
     * 导入关系:
     * ServiceDemo(接口)
     *    |__ServiceDemoImpl1(实例)
     *    |__ServiceDemoImpl2(实例)
     *    |__ServiceDemoImpl3(实例)
     */
    @Autowired
    // 该注解默认是通过 byType方式进行注入的
    // 使用 "通用名称serviceDemo" 报错：异常
    // private ServiceDemo serviceDemo;
    // 修改方式1 -> 使用 "具体实现类的名称" 进行命名：正常，等价于：@Service("serviceDemoImpl3")
    // public ServiceDemo serviceDemoImpl3;
    // 修改方式2
    // 在 ServiceDemoImpl3 的声明中添加：@Service("serviceDemoImpl3")
    @Qualifier("serviceDemoImpl3")
    public ServiceDemo serviceDemo;

}
