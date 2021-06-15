package com.shangcg.mode.adapter;

/**
 * 高级媒体播放器实现类2
 */
public class Mp4Player implements AdvancedMediaPlayer{

    @Override
    public void playVlc(String fileName) {
        //nothing to do
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("playing mp4 file.name:" + fileName);
    }
}
