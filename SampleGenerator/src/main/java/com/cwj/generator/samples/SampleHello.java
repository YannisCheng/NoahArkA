package com.cwj.generator.samples;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * @author ChengWenjia
 * @date 2022-06-08 14:22
 */
public class SampleHello {
    public static void main(String[] args) throws IOException {
        //baseInfo();
        sayHello();
    }

    public static void baseInfo() throws FileNotFoundException {
        // debug环境下，获取resource下的文件
        // resouce: /Users/yannischeng/JetBrain_Projects/Projects_IDEA_Java/NoahArkA/ModuleGenerator/build/resources/main/samples/hello.vm
        File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "samples/hello.vm");
        System.out.println("resouce: " + file.getAbsolutePath());

        // 获取项目本地路径
        // resouce: /Users/yannischeng/JetBrain_Projects/Projects_IDEA_Java/NoahArkA
        String file2 = System.getProperty("user.dir") + "/ModuleGenerator/src/main/java";
        System.out.println("resouce: " + file2);

        String pkName = "com.cwj.genetator";
        String javaPath = System.getProperty("user.dir") + "/ModuleGenerator/src/main/java/";
        String path = javaPath + pkName.replace(".", "/");
        // path: /Users/yannischeng/JetBrain_Projects/Projects_IDEA_Java/NoahArkA/ModuleGenerator/src/main/java/com/cwj/genetator
        System.out.println("path: " + path);

        // jar/war包中获取文件
        //this.getClass().getResourceAsStream("samples/hello.vm");
    }

    public static void sayHello() throws IOException {

        VelocityEngine velocityEngine = new VelocityEngine();
        // 指定使用ClasspathResourceLoader来加载vm文件
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        // 1 初始化
        velocityEngine.init();

        // 2 创建context
        VelocityContext velocityContext = new VelocityContext();

        // 3 向context添加数据对象
        velocityContext.put("content", "word!");

        // 4 选择模板
        Template template = velocityEngine.getTemplate("/samples/hello.vm");

        // 5 合并模板和数据
        //StringWriter stringWriter = new StringWriter();
        //template.merge(velocityContext, stringWriter);

        FileWriter fileWriter = new FileWriter("helloAnno.java");
        BufferedWriter bw = new BufferedWriter(fileWriter);
        template.merge(velocityContext,bw);

        // 6 输出
        bw.flush();
        bw.close();
        //System.out.println(stringWriter.toString());
    }
}
