# 1 Android端埋点-涉及设计思想、技术点总结

2021-02-18 16:22:01  周四

## 1. 前言

Android端 `控件点击事件埋点` 实现 `流程节点` 及 `对应技术`。

![功能实现流程](/images/功能实现流程.png)




## 2. 编程思想 — AOP

- [滴滴DoKit-Android核心原理揭秘之AOP字节码实现](https://juejin.cn/post/6891887877613944840)
- [1分钟让你明白AOP是什么及它的好处](https://blog.csdn.net/LJXZDN/article/details/79404353?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-7.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-7.control)
- [SpringBoot-3 切面AOP实现权限校验：实例演示与注解全解](https://blog.csdn.net/mu_wind/article/details/102758005)
- [关于 Spring AOP (AspectJ) 你该知晓的一切](https://blog.csdn.net/javazejian/article/details/56267036?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-3.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-3.control)


### 2.1 AOP是什么？


**`AOP（Aspect Oriented Programming）`** ：面向切面编程，是OOP的延续。通过`预编译方式`和`运行期动态代理`实现程序功能的`统一维护`的一种技术。

通俗理解为：是能够让我们在`不影响`原有功能的前提下,为软件提供 `横向扩展功能`。`横向`这个词表述的就是`业务逻辑`的`垂直方向`。

`AOP`的出现确实是解决了`外围业务`代码与`核心业务`代码`分离`的问题，但它并`不会`替代OOP。如果说`OOP`的出现是把编码问题进行`模块化`，那么`AOP`就是把涉及到众多模块的`某一类`问题`进行统一管理`。


### 2.2 AOP中使用的标准术语术语

- Aspect（切面）
- Join point（连接点）
- Pointcut（切点）
- Advice（增强）
- Target（目标对象）
- Weaving（织入）


具体描述为：

- `切面(Aspect)` 就是：与业务逻辑 `独立`，但又 `垂直` 存在于 `业务逻辑` 的代码结构中的 `通用功能组合`；
- `切面(Aspect)` 与`业务逻辑`相交的点就是 `切点(Pointcut)`；
- `连接点(Joint point)` 就是：把业务逻辑 `离散化` 后的 `关键节点`,`切点(Pointcut)`属于`连接点`，是连接点的子集；
- `增强(Pointcut)` 就是：`切面`在`切点`上要执行的功能中`新增的具体操作`；
- `织入`指的就是：在`切点`上可以把要完成增强操作的`目标对象(Target)`连接到`切面`中的`过程`。


### 2.3 具体实现AOP的步骤

- 确定 `切入点`。
- 确定 `切入时机`，是业务代码执行前还是执行后。
- 确定切入后的 `具体操作`。


### 2.4 通过图理解AOP

#### 2.4.1 AOP体系：


![AOP](/images/AOP.png)


#### 2.4.2 AOP中各个概念所处的与场景：

![AOP神图](/images/AOP神图.png)



## 3. 设计模式 — Visitor(访问)

### 3.1 设计原则[SOLID](https://insights.thoughtworks.cn/understand-solid-principles/)


- **S**ingle Responsibility Principle：单一职能原则
- **O**pen Close Principle：开闭原则
- **L**iskov Substitution Principle：里氏替换原则
- **I**nterface Segregation Principle：接口隔离原则
- **D**ependency Inversion Principle：依赖倒置原则


### 3.2 [Visitor模式](https://www.cnblogs.com/zyrblog/p/9244754.html) 

#### 3.2.1 什么是Visit？

常用的 `遍历` 就是访问方式的一种，`读取` 到想要查看的 `内容` 并对其进行 `处理` 就叫做访问。

#### 3.2.2 为什么需要Visitor模式？

在OOP中，我们使用 `类` 来封装 `属性`、`各种操作`，而这都封装在类的内部。但是当我们想要使用另一种遍历方式来 `访问该类` 时要怎么办呢，我们就必须将这个类进行修改，但是这违背了设计原则。

如果将 `访问操作单独独立出来` 变成一个新类(ClassVisitor)，当我们需要对一个原有类(TargetClass)进行 `访问操作` 时，直接 `新增` 一个该类(ClassVisitor)即可，而原有类的(TargetClass)的代码不会发生任何改变。

`Visitor模式` 就是解决该类问题的实现方式。

#### 3.2.3 什么是Visitor模式？
 
- [Visitor_pattern](https://en.wikipedia.org/wiki/Visitor_pattern)
- [访问者模式一篇就够了](https://www.jianshu.com/p/1f1049d0a0f4)
 
是一种将 `数据操作` 与 `操作对象(数据)结构` **分离** 的一种方法。 
 
- 该 `分离` 可以在不修改现有结构的情况下向对象结构添加新操作。
- 该 `分离` 遵循 `开闭原则`。

#### 3.2.4 使用前提

被访问对象结构比较稳定。

#### 3.2.5 Visitor模式解决了什么问题？

为 `操作对象` 的类定义`新的操作`(即：定义一个`Visitor对象`，在 `操作对象结构` 的 `元素` 上实现 `执行操作`<重复出现的设计问题>)，而无需修改原有的类。



## 4. Java字节码结构


java运行示意图：

![](/images/16136373735467.png)



### 4.1 字节码结构概览：

![](/images/16136373465646.png)


### 4.2 字节码的具体查看方法

示例：



```Java
package com.cwj.asm.utils;

public class ByteCodeDemo {
    private int a = 1;

    public int add() {
        int b = 2;
        int c = a + b;
        System.out.println(c);
        return c;
    }
}
```


#### 4.2.1 命名行中使用命令

- [javap的基本用法](https://blog.csdn.net/hantiannan/article/details/7659904)

进入当前文件所在目录后，使用命令：


```
javac ByteCodeDemo.java 
javap -verbose ByteCodeDemo
```

结果：


```
警告: 二进制文件ByteCodeDemo包含com.cwj.asm.utils.ByteCodeDemo
Classfile /Users/yannischeng/JetBrain_Projects/Projects_IDEA_Java/AsmProject/src/main/java/com/cwj/asm/utils/ByteCodeDemo.class
  Last modified 2021-2-18; size 445 bytes
  MD5 checksum ad3a0aaeadbbe94029b6a34747ac75ef
  Compiled from "ByteCodeDemo.java"
public class com.cwj.asm.utils.ByteCodeDemo
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #6.#17         // java/lang/Object."<init>":()V
   #2 = Fieldref           #5.#18         // com/cwj/asm/utils/ByteCodeDemo.a:I
   #3 = Fieldref           #19.#20        // java/lang/System.out:Ljava/io/PrintStream;
   #4 = Methodref          #21.#22        // java/io/PrintStream.println:(I)V
   #5 = Class              #23            // com/cwj/asm/utils/ByteCodeDemo
   #6 = Class              #24            // java/lang/Object
   #7 = Utf8               a
   #8 = Utf8               I
   #9 = Utf8               <init>
  #10 = Utf8               ()V
  #11 = Utf8               Code
  #12 = Utf8               LineNumberTable
  #13 = Utf8               add
  #14 = Utf8               ()I
  #15 = Utf8               SourceFile
  #16 = Utf8               ByteCodeDemo.java
  #17 = NameAndType        #9:#10         // "<init>":()V
  #18 = NameAndType        #7:#8          // a:I
  #19 = Class              #25            // java/lang/System
  #20 = NameAndType        #26:#27        // out:Ljava/io/PrintStream;
  #21 = Class              #28            // java/io/PrintStream
  #22 = NameAndType        #29:#30        // println:(I)V
  #23 = Utf8               com/cwj/asm/utils/ByteCodeDemo
  #24 = Utf8               java/lang/Object
  #25 = Utf8               java/lang/System
  #26 = Utf8               out
  #27 = Utf8               Ljava/io/PrintStream;
  #28 = Utf8               java/io/PrintStream
  #29 = Utf8               println
  #30 = Utf8               (I)V
{
  public com.cwj.asm.utils.ByteCodeDemo();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: iconst_1
         6: putfield      #2                  // Field a:I
         9: return
      LineNumberTable:
        line 3: 0
        line 4: 4

  public int add();
    descriptor: ()I
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=1
         0: iconst_2
         1: istore_1
         2: aload_0
         3: getfield      #2                  // Field a:I
         6: iload_1
         7: iadd
         8: istore_2
         9: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
        12: iload_2
        13: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
        16: iload_2
        17: ireturn
      LineNumberTable:
        line 7: 0
        line 8: 2
        line 9: 9
        line 10: 16
}
SourceFile: "ByteCodeDemo.java"
```

**其中**：

- `#` 以及 `数字` 表示的是类中 `类`、`方法`、`字段` 在 `常量池` 中的 `索引`，根据索引可以找到对应的方法、类、字段等信息。

- `Code区` 中的 `编号数字(0: iconst_2)` 表示的是：`.java源文件` 中的方法源代码经过编译器编译后，在JVM中真正执行的 `十六进制操作码`，而编号后面的 `单词(iconst_2)` 则是方便人们理解 `十六进制操作码` 所对应的 `助记符`。

- `line 7: 0` 表示的是：将 `操作码` 与 `.java源文件` 中的 `行号` 进行对应。

- `istore_2` 中的 `2` 表示为：在局部变量表中的 `该变量` 所在插槽的 `插槽(Slot)索引`。


#### 4.2.2 使用插件 — Show Bytecode with jclasslib

可以将该插件的结果看作为 `命令行中命令` 的 `翻译` 版本。

![](/images/16136367443528.png)


#### 4.2.3 使用IDEA自带功能 — Show Bytecode

字节文件中的字节码指令的表示形式。

![](/images/16136370084291.png)

## 5. [ASM](https://asm.ow2.io/)

`ASM库` 的基本实现是：对 `已编译类` 的 `字节码数组` 使用 `Visitor模式`。

`ASM` 的名字没有任何含义：它只是引用 `C` 语言中的 `__asm__` 关键字，这个关键字允许执行一些用 `汇编语言` 编写的 `函数`。

### 5.1 是什么？

`ASM库` 是 `字节码操作框架`，其目的是 `生成`、`转换` 和 `分析` 以 `字节数组` 表示的 `已编译Java类`。

### 5.2 2种API模式

官方用户手册：[asm4-guide.pdf](https://asm.ow2.io/asm4-guide.pdf)

- **Core API**：基础；以基于事件的形式来表示类；运行速度快，占用内存空间少；操作较难。
- **Tree API**：封装，以基于对象的形式来表示类。


### 5.3 [字节码增强](https://www.cnblogs.com/meituantech/p/11497262.html)

**字节码增强** 就是一类对 `现有字节码` 进行 `修改` 或者 `动态生成全新` `字节码文件` 的技术。

对于 `Android端埋点` 功能的实现大部分是通过该 `字节码增强` 技术实现的。其中 `点击事件` 埋点功能的具体实现中还使用了 `AOP（面向切面编程）` 思想。

`字节码增强` 技术的实现方式之一就是通过 `ASM` 框架。

### 5.4 对ASM库初学者的建议

最好不要直接在 `Android Studio` 中学习 `ASM`库的使用，而是在 `IDEA` 或者其他开发工具中引入 `ASM库`，从最简单的方式开始学习使用，可以因此规避掉一些由于其他因素的导致的异常，提高学习效率。

### 5.5 IDE中的ASM插件

Asm Bytecode Viewer

## 6. [Gradle](https://docs.gradle.org/current/userguide/userguide.html) [Transform](https://google.github.io/android-gradle-dsl/javadoc/2.1/com/android/build/api/transform/Transform.html)

- [开发自定义Gradle插件](https://docs.gradle.org/current/userguide/custom_plugins.html)
- [一文学会Android Gradle Transform基础使用](https://juejin.cn/post/6914485867029463054)
- [Gradle笔记-自定义插件](https://ljd1996.github.io/2019/08/16/Gradle%E7%AC%94%E8%AE%B0/#%E8%87%AA%E5%AE%9A%E4%B9%89%E6%8F%92%E4%BB%B6)
- [如何理解SOLID原则？](https://insights.thoughtworks.cn/understand-solid-principles/)
- [Android如何创建Gradle插件开发工程及调试](https://blog.csdn.net/xialonghua/article/details/93643648)
- [在Android Studio中调试Gradle插件](https://www.milovetingting.cn/2020/02/25/Android/%E5%9C%A8Android%20Studio%E4%B8%AD%E8%B0%83%E8%AF%95Gradle%E6%8F%92%E4%BB%B6/)






