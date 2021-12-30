# Annotation&Reflection&APT

2021-12-23 11:45:18 周四

## 概览

### 内容大纲

- **Annotation使用**
- **Reflection使用**
- **APT(Annotation Processing Tool)创建及注册**
- **JavaPoet框架使用**

### 参考

**JavaPoet Github**:

- [square/javapoet](https://github.com/square/javapoet)

**javac文章**：

- [Java程序的编译过程](cnblogs.com/code-duck/p/13568092.html)
- [Java注解处理器](https://www.race604.com/annotation-processing/)
- [关于Javac命令和类文件之间的依赖问题 （找不到程序包）](https://blog.csdn.net/meinanqihao/article/details/97613440)


**博客参考**：

- [AbstractProcessor 用法示例](https://blog.51cto.com/terryrao/1654812)
- [Java：注解和反射](https://www.cnblogs.com/opendragonhuang/p/11225026.html)
- [自定义Annotation-基础](https://www.cnblogs.com/wondertwo/p/6017409.html)
- [自定义Annotation-提高-APT(Annotation Processing Tool)](cnblogs.com/wondertwo/p/6017403.html)
- [注解与反射：Source级别](https://blog.csdn.net/xihuailu3244/article/details/107749381)
  
- [JavaPoet - 优雅地生成代码](https://blog.csdn.net/xuguobiao/article/details/72775730)
- [编译时注解处理器 ProcessingEnvironment相关方法](https://blog.csdn.net/xianrenli38/article/details/121076748)
- [深入理解Java注解（二）——JavaPoet使用](https://www.136.la/jingpin/show-204052.html)


**APT注册**：

- [Java中怎么实现一个 @Retention 为 SOURCE（只在源码阶段保留） 的注解？](https://www.zhihu.com/question/275833833)
- [Annotation Processor 在 Maven Gradle里的配置](https://www.jianshu.com/p/38a1c3917745)
- [IDEA+Gradle使用Annotation Processor](https://blog.csdn.net/qq_40985294/article/details/90041296)
- [AbstractProcessor 用法示例](https://blog.51cto.com/terryrao/1654812)

---

## Annotation

3个生命周期：

- **source**：**用于在执行`javac`过程的中做检查或生成中间模板代码，可结合 `Java APT技术`。**
- **class**：**用于对class（字节码）做扩展处理，如`ASM` 字节码操作框架。**
- **runtime**：**需要在运行时去动态获取注解的Java源文件信息，需要搭配`Reflection（反射）`方式进行使用。**

Annotation中3个不同的生命周期，对应着Java源码编译过程及使用的不同阶段，在做使用分析、性能分析、耗时分析时应该明确分析的基础点（过程阶段）是什么。

![annotation-retention表格](../../images/basic/retention%20表格.png)

### source


![Javac 编译器的3个处理过程](../../images/basic/Javac%20编译器的3个处理过程.png)

被该种类型注解的Java源文件，需要搭配 `APT（Annotation Processing Tool）` 方式在 `javac` 的 `编译时（注解处理器（Annotation Processor）阶段）` 中进行使用。

源码注解(RetentionPolicy.SOURCE)的生命周期只存在Java源文件这一阶段，是3种生命周期中最短的注解。
当在Java源程序上加了一个注解，这个Java源程序要由javac去编译，javac把java源文件编译成.class文件，在编译为class时会把Java源程序上的源码注解给去掉。 需要注意的是：在编译器`处理期间``源码注解``还存在`
，即注解处理器Processor 也能处理源码注解，编译器`处理完之后`就没有该注解信息了。

### class


### runtime

## Reflection

## APT(Annotation Processing Tool

在Javac执行前生成辅助代码文件,是一种在代码编译时处理注解，按照一定的规则，生成相应的java文件，多用于对自定义注解的处理。 目前比较流行的Dagger2, ButterKnife,
EventBus3都是采用APT技术，对运行时的性能影响很小。

### 实现1 java7前 - 重写4个方法

需要继承 `AbstractProcessor` 抽象类，重写4个方法,代码如下：

```java
public class RouteProcessor extends AbstractProcessor {

    /**
     * 一个注解处理器类都必须有一个空的构造函数
     */
    @Override
    public synchronized void init(ProcessingEnvironment env) {
    }

    /**
     * 这相当于每个处理器的主函数main()。
     * 你在这里写你的扫描、评估和处理注解的代码，以及生成Java文件。
     */
    @Override
    public boolean process(Set<? extends TypeElement> annoations, RoundEnvironment env) {
    }

    /**
     * 这里必须指定，这个注解处理器是注册给哪个注解的。
     * 注意，它的返回值是一个字符串的集合，包含本处理器想要处理的注解类型的合法全称。
     * 换句话说，在这里定义你的注解处理器注册到哪些注解上。
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
    }

    /**
     * 用来指定你使用的Java版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
    }
}
```

### 实现2 java7后 - 重写2个方法

```java

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("com.cwj.common.anno_ref_apt.annotation.BindView")
public class BookApt7 extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
```

### 处理器注册（重点）

基本描述：

```text
javac 在进行编译的时候会先查找存在的注解处理器（Annotation Processor），通常它们都会预编译好并且打包成 .jar 放在项目依赖中。
在编译之前会使用这些注解处理器处理源代码。

你当前只是定义了一个不完整的注解，但是没有编写相应的注解处理器，你下一步需要做的是实现一个注解处理器。
例如你可以继承 AbstractProcessor 然后实现它的几个方法，这样子便完成了一个注解处理器。
不过 javac 仍然不会调用你的注解处理器，因为你没有注册。
在打包的时候创建文件：
META-INF/services/javax.annotation.processing.Processor 
并且将自定义的注解处理器写入此文件进行注册，
例如：package_name.Processor1
package_name.Processor2
package_name.Processor3 

如果你并不想打包，也无法注册。
也就是在 javac 无法自动查找到注解处理器的情况下仍然想使用自己编写的注解处理器，那么你可以给 javac 加参数进行指定：
javac -processor package_name.Processor Source1.java

有关注解处理器的实现我已经提过了 AbstractProcessor，打包时自动进行处理器注册可以使用 Google 的 @AutoService。
maven/gradle 等构建工具都有对 Annotation Processor 的配置和支持，包括 Eclipse 和 Intellij IDEA 也有。
```

#### 方式1：未注册，手动调用javac进行编译

在未向javac注册 `APT` 的情况下，手动调用APT：

```shell
javac com/cwj/anno/apt/HelloProcess.java 
javac -processor com.cwj.anno.apt.HelloProcess com/cwj/anno/HelloMain.java
```

#### 方式2：自动注册

[Annotation Processor 在 Maven Gradle里的配置](https://www.jianshu.com/p/38a1c3917745)

##### 使用及配置描述

|工程名称|作用描述|
|---|---|
|SampleAnnoSource|包含`注解`和`注解处理器`的工程，以远程nexus仓库中jar包的形式对外提供服务|
|ModuleCommon|使用预设的注解，并且希望在编译的时候，执行注解处理器进行一些操作|

##### SampleAnnoSource工程：创建 AnnotationProcessing

*Gradle配置*：将jar上传至nexus仓库

```groovy
publishing {
    publications {
        mavenJava(MavenPublication) {
            groupId = POM_GROUPID
            artifactId = POM_NAME
            version = POM_VERSION
            from components.java
        }
    }

    repositories {
        maven {
            allowInsecureProtocol = true
            url NEXUS_REPOSITORY_URL
            credentials {
                username = NEXUS_USERNAME
                password = NEXUS_PASSWORD
            }
        }
    }
}
```

##### ModuleCommon工程：使用 AnnotationProcessing

*Gradle配置*：导入 注解处理的所在jar包的依赖。

```groovy
repositories {
    maven {
        allowInsecureProtocol = true
        url NEXUS_REPOSITORY_URL
        credentials {
            username = NEXUS_USERNAME
            password = NEXUS_PASSWORD
        }
    }
}

dependencies {
    // 含有 AnnotationProcessing 的jar包
    implementation 'com.cwj:anno_source:1.0.1:plain@jar'
    // 注解处理器所在的包
    annotationProcessor 'com.cwj:anno_source:1.0.1:plain@jar'
}

// 声明编译过程中需要用到的 AnnotationProcessing
compileJava.options.compilerArgs << "-processor" << "com.cwj.anno.apt.HelloProcess"
```

#### 测试方式

```java
@HelloAnno
public final class HelloWord {
  public static void main(String[] args) {
    System.out.println("Hello Word");
  }
}
```


## JavaPoet框架

### 添加依赖

`implementation 'com.squareup:javapoet:1.13.0'`

### 关键类说明

| class | 说明 |
| --- | --- |
|JavaFile|	A Java file containing a single top level class	用于构造输出包含一个顶级类的Java文件|
|TypeSpec	|A generated class, interface, or enum declaration	生成类，接口，或者枚举|
|MethodSpec	|A generated constructor or method declaration	生成构造函数或方法|
|FieldSpec	|A generated field declaration	生成成员变量或字段|
|ParameterSpec|	A generated parameter declaration	用来创建参数|
|AnnotationSpec	|A generated annotation on a declaration	用来创建注解|


