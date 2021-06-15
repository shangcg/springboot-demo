package com.shangcg.mode.bridge;

/**
 * 创建内部使用桥接接口抽象类的实现
 * shape:图形 抽象部分
 * circle：圆 实现部分
 * 抽象部分和实现部分分离
 */
public class Circle extends Shape{
    private int x, y, radius;
    protected Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}
