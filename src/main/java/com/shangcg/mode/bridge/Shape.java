package com.shangcg.mode.bridge;

/**
 * 创建抽象类 内部使用桥接接口
 */
public abstract class Shape {

    protected DrawAPI drawAPI;
    protected Shape(DrawAPI drawAPI){
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
