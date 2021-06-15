package com.shangcg.mode.adapter;

/**
 * 高级媒体播放器实现类
 */
public class VlcPlayer implements AdvancedMediaPlayer{

    @Override
    public void playVlc(String fileName) {
        System.out.println("playing vlc file.name" + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // nothing to do
    }
}
