package com.cwj.asm.core_api.method_type.cost_simple;

public class TestMain {

    // 2021-08-06 10:30:25 封闭
    /*public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        *//*
         * 通过自定义ClassLoader进行测试
         * 运行结果：
         *
         * statTime is :1612507599918
         * endTime is :1612507600121
         * method costMethodName cost time is : 203 ms
         *//*
        CostClassLoader myClassLoader = new CostClassLoader();
        Class<?> loadClass = myClassLoader.loadClass("TargetTest");
        Method method = loadClass.getDeclaredMethod("targetMethod", (Class<?>) null);
        Method method2 = loadClass.getDeclaredMethod("targetMethod2", (Class<?>) null);
        Object ob = method.invoke(loadClass.newInstance(), (Object) null);
        Object ob2 = method2.invoke(loadClass.newInstance(), (Object) null);
    }*/
}

