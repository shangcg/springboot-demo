package com.shangcg.mode.decorator;

/**
 * 实现shape接口的抽象装饰类
 *
 * 该类为装饰类，持有被装饰接口对象
 */
public abstract class ShapeDecorator implements Shape{
    protected Shape decoratorShape;
    public ShapeDecorator(Shape decoratedShape){
        this.decoratorShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratorShape.draw();
    }
}
