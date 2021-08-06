package com.cwj.asm.core_api.metadata_type.annotation;

import com.cwj.asm.core_api.metadata_type.annotation.visits.ClassCustom;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

/**
 * AnnotaMainTest  ASM 元数据-注解示例测试
 *
 * @author  wenjia.Cheng  cwj1714@163.com
 * @date    2021/2/7 14:12
*/
public class AnnotaMainTest {

    // 2021-08-06 10:30:45 封闭
    /*public static void main(String[] args) throws IOException {
        *//*TargetAnnotaClass annotaClass = new TargetAnnotaClass();
        annotaClass.printAppleColor();
        annotaClass.printOrangeColor();
        annotaClass.printBananaColor(AnnotaFruitColor.Color.BULE);*//*
        parsingClass();
    }*/

    public static void parsingClass() throws IOException {
        ClassWriter classWriter = new ClassWriter(0);
        ClassCustom classCustom = new ClassCustom(classWriter);
        ClassReader classReader = new ClassReader("com.cwj.asm.core_api.metadata_type.annotation.TargetAnnotaClass");
        classReader.accept(classCustom, 0);
    }
}


