package com.shangcg.mode.decorator;

/**
 * 被装饰者
 * 装饰器模式：接口实现类
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Shape Rectangle");
    }
}
