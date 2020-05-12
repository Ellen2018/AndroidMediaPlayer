package com.ellen.androidmediaplayer;

import com.ellen.mediaplaylibrary.base.BaseMediaPlayer;
import com.ellen.mediaplaylibrary.base.playmode.BasePlayMode;

import java.util.List;

public class MyMediaPlayer extends BaseMediaPlayer<Music> {

    public MyMediaPlayer(List<BasePlayMode> basePlayModeList, int position) {
        super(basePlayModeList, position);
    }

    @Override
    public String getPath(Music music) {
        return music.getPath();
    }
}
