package com.shangcg.mode.bridge.exercise;

/**
 * 桥接模式抽象类
 * 变化角度： 资源类型  此处为合同
 */
public abstract class Resource {

    /**
     * 持有桥接顶层接口： 审批动作对象
     */
    ApproveAPI approveAPI;
    public Resource(ApproveAPI approveAPI){
        this.approveAPI = approveAPI;
    }

    /**
     * 审批
     */
    public abstract void approve();

}
