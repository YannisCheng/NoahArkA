package com.cwj.genesis.aop.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * com.cwj.genesis.aop.java_proxy
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-05-26 13:55
 */
public class JDKProxy {

    public static void main(String[] args) {

        // 创建被代理的对象
        UserDaoImpl userDao = new UserDaoImpl();
        // 声明被代理的接口
        Class[] interfaces = {UserDao.class};
        // 理解关键点：接口实现类的代理对象，在这个代理对象中对被代理接口实现类的每一个方法进行增强。。
        UserDao proxyInstance = (UserDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 此处如果对 method 进行进一步处理就是用到了反射。
                System.out.println("方法之前执行。。。" + method.getName() + "args : " + Arrays.toString(args));
                // 当proxyInstance对象调用哪个方法时，此处就执行哪个方法。
                // 所谓的“方法增强”就是在“被调用方法上下”进行代码处理
                Object invoke = method.invoke(userDao, args);
                System.out.println("方法之后执行。。。" + invoke);

                return invoke;
            }
        });

        // 接口实现类的代理对象 调用接口中的 add()
        int add = proxyInstance.add(1, 2);
        System.out.println("result: " + add);
        /*
         * 运行结果：
         * 方法之前执行。。。addargs : [1, 2]
         * 方法之后执行。。。3
         * result: 3
         */

        // 接口实现类的代理对象 调用接口中 的update()
        String newUpdate = proxyInstance.update("new update");
        System.out.println("update new method: " + newUpdate);
        /*
         * 运行结果：
         * 方法之前执行。。。updateargs : [new update]
         * 方法之后执行。。。new update
         * update new method: new update
         */
    }
}
