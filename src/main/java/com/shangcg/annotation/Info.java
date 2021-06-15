package com.shangcg.annotation;


import java.lang.annotation.*;

/**
 * 注解的几个要素
 * 用于注解的元注解
 * 1 @target表明该注解可以应用的java元素类型  2@retention表明该注解生命周期
 * 3@Document该注解声明的元素可以被javadoc生成文档  @inherited被该注解标注的类的子类具有该属性
 * 1：修饰符 public  2: 注解关键字@interface  3:注解名称
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface Info{

    String value() default "tracy";

    boolean isDelete();
}
