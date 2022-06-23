package com.cwj.anno.poet;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;

/**
 * com.cwj.anno.poet
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-12-31 13:51
 */
public class PoetMain {

    public static void main(String[] args) {
        javaPoetSample();
    }

    /**
     * 通过使用JavaPoet框架来生成HelloWord.java代码样例
     */
    private static void javaPoetSample() {

        // 创建方法
        MethodSpec mainMethod = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello Word")
                .returns(void.class)
                .build();

        // 创建类
        TypeSpec helloWord = TypeSpec.classBuilder("HelloWord")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(mainMethod)
                .build();

        // 创建类文件
        // com.cwj.common.anno_ref_apt.apt.out
        JavaFile javaFile = JavaFile.builder("com.cwj.common.anno_ref_apt.poet.out", helloWord).build();

        try {
            // 通过控制台输出文件内容
            // javaFile.writeTo(System.out);
            // 如果是输出到指定目录下的文件，不需要填写目标文件所在的包名，如果加上会导致包名路径重复。
            File file = new File("/Users/yannischeng/JetBrain_Projects/Projects_IDEA_Java/NoahArkA/ModuleCommon/src/main/java/");
            if (!file.exists()) {
                file.mkdirs();
            } else {
                file.delete();
            }
            javaFile.writeTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
