package com.ellen.mediaplaylibrary.base;

import java.util.List;

public class PlayStatus<T> {

    /**
     * 是否播放
     */
    private boolean isPlaying = false;
    /**
     * 播放列表
     */
    private List<T> playList = null;
    /**
     * 播放位置
     */
    private int position = -1;
    /**
     * 播放总时间(单位:毫秒)
     */
    private int duration = 0;
    /**
     * 当前曲目播放的时间(单位:毫秒)
     */
    private int currentDuration = 0;

    public boolean isPlaying() {
        return isPlaying;
    }

    public List<T> getPlayList() {
        return playList;
    }

    public int getPosition() {
        return position;
    }

    public int getDuration() {
        return duration;
    }

    public int getCurrentDuration() {
        return currentDuration;
    }

    void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    void setPlayList(List<T> playList) {
        this.playList = playList;
    }

    void setPosition(int position) {
        this.position = position;
    }

    void setDuration(int duration) {
        this.duration = duration;
    }

    void setCurrentDuration(int currentDuration) {
        this.currentDuration = currentDuration;
    }

    public boolean isPlayerNull(){
        return playList == null || position == -1;
    }
}
