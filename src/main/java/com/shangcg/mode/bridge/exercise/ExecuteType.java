package com.shangcg.mode.bridge.exercise;

/**
 * 审批特批类型 审批执行类型 ->类比 画蓝色
 */
public class ExecuteType implements ApproveAPI {
    @Override
    public void approve() {
        System.out.println("审批执行类型");
    }
}
