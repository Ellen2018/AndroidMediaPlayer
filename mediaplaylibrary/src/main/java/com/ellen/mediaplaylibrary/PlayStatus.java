package com.ellen.mediaplaylibrary;

import java.util.List;

public class PlayStatus<T> {

    private boolean isPlaying = false;
    private List<T> playList = null;
    private int position = -1;
    private int duration = 0,currentDuration = 0;

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
