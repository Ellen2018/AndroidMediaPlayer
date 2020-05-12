package com.ellen.mediaplaylibrary.base;

import java.util.List;

public interface MediaPlyerInterface<T>{

    /**
     * 播放或者暂停
     */
    void startOrPause();

    /**
     * 下一曲
     */
    void next();
    void nextByUser();

    /**
     * 上一曲
     */
    void pre();
    void preByUser();

    /**
     * 开始播放
     */
    void start();

    /**
     * 暂停播放
     */
    void pause();

    /**
     * 停止播放
     */
    void stop();

    /**
     * 跳转到time时间
     * @param time
     */
    void seekTo(int time);

    /**
     * 播放器设置播放列表，且指定位置
     * @param position
     * @param tList
     */
    void openNewList(int position, List<T> tList);

    /**
     * 播放列表指定位置的歌曲
     * @param position
     */
    void openCurrentPosition(int position);

    /**
     * 获取音乐播放地址
     * @param t
     * @return
     */
    String getPath(T t);

    /**
     * 下一曲播放(单曲)
     * @param t
     */
    void addMusicToNext(T t);

    /**
     * 下一曲播放(集合)
     * @param tList
     */
    void addMusicListToNext(List<T> tList);

    /**
     * 添加播放监听事件
     * @param playedListener
     */
    void addPlayedListener(BaseMediaPlayer.MediaPlayerListener<T> playedListener);

    /**
     * 移除播放监听事件
     * @param playedListener
     */
    void removePlayedListener(BaseMediaPlayer.MediaPlayerListener<T> playedListener);

    /**
     * 清空播放列表
     */
    void clear();

    /**
     * 调整播放模式
     */
    void adjustPlayMode();

    /**
     * 获取到当前播放器的状态
     * @return
     */
    PlayStatus<T> getPlayStatus();
}
