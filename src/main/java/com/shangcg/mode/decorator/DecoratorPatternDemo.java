package com.shangcg.mode.decorator;

/**
 * 装饰器模式 使用demo
 */
public class DecoratorPatternDemo {

    public static void main(String[] args) {
        Shape circle = new Circle();

        // new 一个装饰器 装饰器的构造函数持有被装饰者对象
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());

        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle of red border");
        redCircle.draw();

        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }
}
