package annotation;

import util.TradeStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * 这是一个自定义注解类（Annotation）。在定义注解类时使用了另一个注解类@Retention。
 * 在注解类上使用另一个注解类，那么被使用的注解类成为元注解。
 *
 * 一个注解的生命周期有三个阶段：java源文件是一个阶段，class文件是一个阶段，内存中的字节码是一个阶段。
 * javac把java源文件编译成.class文件时，有可能去掉里面的注解，类加载器把.class文件加载到内存时也有可能去掉里面的注解.
 * 因此在自定义注解时就可以使用Retention注解指明自定义注解的生命周期.
 * 自定义注解的生命周期是在RetentionPolicy.SOURCE阶段(java源文件阶段)，还是在RetentionPolicy.CLASS阶段(class文件阶段)，或者是在RetentionPolicy.RUNTIME阶段(内存中的字节码运行时阶段)，
 * 根据JDK提供的API可以知道默认是在RetentionPolicy.CLASS阶段 (JDK的API写到：the retention policy defaults to RetentionPolicy.CLASS.)
 *
 *
 * Retention注解决定了MyAnnotation注解类的生命周期
 * RetentionPolicy.SOURCE 注解存在于Java源文件，编译成.class文件后注解就不存在了
 * RetentionPolicy.CLASS 注解在.java源文件，编译成.class文件后也存在。当类被类加载器加载到内存中就不存在了
 * RetentionPolicy.RUNTIME 注解在程序运行周期内都存在
 * @Retention元注解三种取值：RetentionPolicy.SOURCE、RetentionPolicy.CLASS、RetentionPolicy.RUNTIME分别对应：Java源文件(.java文件) -> .class文件 -> 内存中的字节码
 *
 *
 * Target元注解决定了MyAnnotation注解类可以加在哪些成分上：如加在类/属性/方法上.
 * @Target默认值为任何元素
 *
 *
 * 注解既然是特殊的类，那么可以为类添加属性。注解的属性方法和接口的方法定义一样，而应用注解的类可以认为是实现了这个特殊的接口。
 *
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyAnnotation {

    TradeStatus entryStatus() default TradeStatus.NEW; // 为属性指定默认值，枚举类型的属性

    TradeStatus[] expectedStatus() default {TradeStatus.SUCCESS, TradeStatus.FAIL}; // 数组类型的属性

}
