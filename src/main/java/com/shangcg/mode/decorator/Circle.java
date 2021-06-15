package com.shangcg.mode.decorator;

/**
 * 装饰器模式：接口实现类
 */
public class Circle implements Shape{
    @Override
    public void draw() {
        System.out.println("shape: circle");
    }
}
