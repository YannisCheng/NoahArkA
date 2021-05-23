package com.cwj.asm.core_api.method_type.cost_simple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestMain {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        /*
         * 通过自定义ClassLoader进行测试
         * 运行结果：
         *
         * statTime is :1612507599918
         * endTime is :1612507600121
         * method costMethodName cost time is : 203 ms
         */
        CostClassLoader myClassLoader = new CostClassLoader();
        Class<?> loadClass = myClassLoader.loadClass("TargetTest");
        Method method = loadClass.getDeclaredMethod("targetMethod", null);
        Method method2 = loadClass.getDeclaredMethod("targetMethod2", null);
        Object ob = method.invoke(loadClass.newInstance(), null);
        Object ob2 = method2.invoke(loadClass.newInstance(), null);
    }
}

