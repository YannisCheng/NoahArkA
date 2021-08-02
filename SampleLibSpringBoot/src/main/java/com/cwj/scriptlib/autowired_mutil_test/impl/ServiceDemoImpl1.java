package com.cwj.scriptlib.autowired_mutil_test.impl;

import com.cwj.scriptlib.autowired_mutil_test.ServiceDemo;
import org.springframework.stereotype.Service;

/**
 * com.cwj.scriptlib.autowired.impl
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-02 19:49
 */
@Service
public class ServiceDemoImpl1 implements ServiceDemo {
    @Override
    public void sayHello() {
        System.out.println("impl1");
    }
}
