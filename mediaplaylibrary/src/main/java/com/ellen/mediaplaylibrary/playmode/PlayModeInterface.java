package com.ellen.mediaplaylibrary.playmode;

/**
 * 播放模式的接口
 */
public interface PlayModeInterface {

    int getNextPosition(int position,int size);
    int getNextFromUserPosition(int position,int size);
    int getPrePosition(int position,int size);
    int getPreFromUserPosition(int position,int size);
}
