package com.shangcg.mode.bridge;

/**
 * 桥接模式实现类2
 */
public class GreenCircle implements DrawAPI{
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing circle : green: "+ radius + x + y);
    }
}
