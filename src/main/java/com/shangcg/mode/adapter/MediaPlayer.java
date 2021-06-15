package com.shangcg.mode.adapter;


/**
 * 适配器模式: 不能彻底理解------- 只能理解表象
 * 简介：两个不兼容接口之间的桥梁
 * 意图：将一个类的接口转换成客户希望的另一个接口
 * 何时使用：
 */

/**
 * 媒体播放器接口
 */
public interface MediaPlayer {
    public void play(String audioType, String fileName);
}
