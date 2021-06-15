package com.shangcg.mode.bridge.exercise;

public class Contract extends Resource{
    ApproveAPI approveAPI;
    public Contract(ApproveAPI approveAPI) {
        super(approveAPI);
        this.approveAPI = approveAPI;
    }

    @Override
    public void approve() {
        approveAPI.approve();
    }
}
