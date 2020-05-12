package com.ellen.androidmediaplayer;

import com.ellen.mediaplaylibrary.EllenMediaPlayer;
import com.ellen.mediaplaylibrary.playmode.BasePlayMode;

import java.util.List;

public class MyMediaPlayer extends EllenMediaPlayer<Music> {

    public MyMediaPlayer(List<BasePlayMode> basePlayModeList, int position) {
        super(basePlayModeList, position);
    }

    @Override
    public String getPath(Music music) {
        return music.getPath();
    }
}
