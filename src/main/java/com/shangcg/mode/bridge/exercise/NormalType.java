package com.shangcg.mode.bridge.exercise;

/**
 * 桥接实现类1 审批类型 ->类比画图形的颜色 画红色
 */
public class NormalType implements ApproveAPI {
    @Override
    public void approve() {
        System.out.println("审批常规 类型------");
    }
}
