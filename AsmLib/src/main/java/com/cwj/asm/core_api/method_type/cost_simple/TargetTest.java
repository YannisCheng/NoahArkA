package com.cwj.asm.core_api.method_type.cost_simple;

/**
 * TargetTest  要计算耗时的目标类
 *
 * @author wenjia.Cheng  cwj1714@163.com
 * @date 2021/2/5 14:16
 */
public class TargetTest {

    /**
     * 该字段存在的意义是与 "targetMethod()" 中的 System.out.println(temp);相对应。
     * 因为如果在方法开始出进行了 "方法操作栈-局部变量index的修改"，那么在调用"temp"变量时就会发生异常：类校验错误
     */
    private String temp = "temp_name";

    public void targetMethod() throws InterruptedException {
        // 注意：在进行测试时，不能直接使用"ComputeTargetCost"。
        // 因为这样会导致找不到ComputeTargetCost类。
        // 应该以"全限定名称"的方式进行方法的调用。
        /*String classN = "ComputeTargetCost&targetMethod";
        com.cwj.asm.core_api.method_type.cost_simple.ComputeTargetCost.startTime(classN);*/
        Thread.sleep(200);
        // 注意此处的temp的调用，是知识点：发生了 "aload_0" 内容替换。
        System.out.println(temp);
        //com.cwj.asm.core_api.method_type.cost_simple.ComputeTargetCost.stopTime(classN);
    }

    public void targetMethod2(){
        System.out.println("method2");
    }
}
/*
 * 经过ASM处理后的目标类实现计划为：
 * public class TargetTest {
 *     public TargetTest() {
 *     }
 *
 *     public void targetMethod(String name) throws InterruptedException {
 *         ComputeTargetCost.startTime(name);
 *         Thread.sleep(200L);
 *         ComputeTargetCost.stopTime(name);
 *     }
 * }
 */
