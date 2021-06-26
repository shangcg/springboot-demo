package com.shangcg.mode.decorator;

/**
 * 装饰类 装饰被装饰者shape 动态扩展shape功能，包装类为shapeDecorator
 */
public class RedShapeDecorator extends ShapeDecorator{
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratorShape.draw();
        setRedBorder(decoratorShape);
    }

    public void setRedBorder(Shape decoratorShape){
        System.out.println("Border Color : Red");
    }
}
