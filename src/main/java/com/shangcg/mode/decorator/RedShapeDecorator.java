package com.shangcg.mode.decorator;

/**
 * 对包装类的扩展，包装类为shapeDecorator
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
