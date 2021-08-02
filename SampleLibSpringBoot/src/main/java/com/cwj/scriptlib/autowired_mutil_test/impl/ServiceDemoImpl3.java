package com.cwj.scriptlib.autowired_mutil_test.impl;

import com.cwj.scriptlib.autowired_mutil_test.ServiceDemo;
import org.springframework.stereotype.Service;

/**
 * com.cwj.scriptlib.autowired.impl
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-02 19:49
 */
@Service("serviceDemoImpl3")
public class ServiceDemoImpl3  implements ServiceDemo {
    @Override
    public void sayHello() {
        System.out.println("impl3");
    }
}
