package com.ellen.mediaplaylibrary.base;

import android.media.MediaPlayer;

import com.ellen.mediaplaylibrary.base.playmode.BasePlayMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseMediaPlayer<T> implements MediaPlyerInterface<T> {

    private Data<T> data;
    private MediaPlayer mediaPlayer;
    private List<MediaPlayerListener<T>> mediaPlayerListenerList;

    public BaseMediaPlayer(List<BasePlayMode> basePlayModeList, int position) {
        //播放模式
        data = new Data<>();
        data.setBasePlayModeList(basePlayModeList);
        data.setPlayMode(basePlayModeList.get(position));
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                start();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                next();
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                next();
                return true;
            }
        });
    }

    @Override
    public void addPlayedListener(MediaPlayerListener<T> mediaPlayerListener) {
        if (mediaPlayerListenerList == null) {
            mediaPlayerListenerList = new ArrayList<>();
        }
        mediaPlayerListenerList.add(mediaPlayerListener);
    }

    @Override
    public void removePlayedListener(MediaPlayerListener<T> mediaPlayerListener) {
        mediaPlayerListenerList.remove(mediaPlayerListener);
    }

    @Override
    public void startOrPause() {
        if (mediaPlayer == null) return;
        if (!data.checkPlayStatus()) {
            if (mediaPlayerListenerList != null) {
                for (MediaPlayerListener<T> mediaPlayerListener : mediaPlayerListenerList) {
                    mediaPlayerListener.playerNull();
                }
            }
            return;
        }
        if (mediaPlayer.isPlaying()) {
            pause();
        } else {
            start();
        }
    }

    @Override
    public void next() {
        if (!data.checkPlayStatus()) {
            if (mediaPlayerListenerList != null) {
                for (MediaPlayerListener<T> mediaPlayerListener : mediaPlayerListenerList) {
                    mediaPlayerListener.playerNull();
                }
            }
            return;
        }
        //检查有没有添加下一曲
        if(data.getAddMusic() != null){
           data.getRecordList().add(data.getRecordPosition() + 1,data.getAddMusic().getPosition());
        }
        int record = data.getRecordPosition();
        record++;
        if (record > data.getRecordList().size() - 1) {
            data.setPosition(data.getPlayMode().getNextPosition(data.getPosition(), data.getPlayList().size()));
            data.setRecordPosition(record);
            data.getRecordList().add(data.getPosition());
            open(data.getRecordList().get(data.getRecordPosition()));
        } else {
            data.setRecordPosition(record);
            open(data.getRecordList().get(data.getRecordPosition()));
        }
    }

    @Override
    public void nextByUser() {
        if (!data.checkPlayStatus()) {
            if (mediaPlayerListenerList != null) {
                for (MediaPlayerListener<T> mediaPlayerListener : mediaPlayerListenerList) {
                    mediaPlayerListener.playerNull();
                }
            }
            return;
        }
        //检查有没有添加下一曲
        if(data.getAddMusic() != null){
            data.getRecordList().add(data.getRecordPosition() + 1,data.getAddMusic().getPosition());
        }
        int record = data.getRecordPosition();
        record++;
        if (record > data.getRecordList().size() - 1) {
            data.setPosition(data.getPlayMode().getNextFromUserPosition(data.getPosition(), data.getPlayList().size()));
            data.setRecordPosition(record);
            data.getRecordList().add(data.getPosition());
            open(data.getRecordList().get(data.getRecordPosition()));
        } else {
            data.setRecordPosition(record);
            open(data.getRecordList().get(data.getRecordPosition()));
        }
    }

    @Override
    public void pre() {
        if (!data.checkPlayStatus()) {
            if (mediaPlayerListenerList != null) {
                for (MediaPlayerListener<T> mediaPlayerListener : mediaPlayerListenerList) {
                    mediaPlayerListener.playerNull();
                }
            }
            return;
        }
        int record = data.getRecordPosition();
        record--;
        if (record < 0) {
            data.setPosition(data.getPlayMode().getPrePosition(data.getPosition(), data.getPlayList().size()));
            data.setRecordPosition(0);
            data.getRecordList().add(0, data.getPosition());
            open(data.getRecordList().get(data.getRecordPosition()));
        } else {
            data.setRecordPosition(record);
            open(data.getRecordList().get(data.getRecordPosition()));
        }
    }

    @Override
    public void preByUser() {
        if (!data.checkPlayStatus()) {
            if (mediaPlayerListenerList != null) {
                for (MediaPlayerListener<T> mediaPlayerListener : mediaPlayerListenerList) {
                    mediaPlayerListener.playerNull();
                }
            }
            return;
        }
        int record = data.getRecordPosition();
        record--;
        if (record < 0) {
            data.setPosition(data.getPlayMode().getPreFromUserPosition(data.getPosition(), data.getPlayList().size()));
            data.setRecordPosition(0);
            data.getRecordList().add(0, data.getPosition());
            open(data.getRecordList().get(data.getRecordPosition()));
        } else {
            data.setRecordPosition(record);
            open(data.getRecordList().get(data.getRecordPosition()));
        }
    }

    @Override
    public void addMusicToNext(T t) {
        if (data.checkPlayStatus()) {
            boolean isSame = false;
            for (T t1 : data.getPlayList()) {
                if (t1.equals(t)) {
                    isSame = true;
                    break;
                }
            }
            if (!isSame) {
                data.getPlayList().add(data.getPosition() + 1, t);
                data.setAddMusic(t,data.getPosition() + 1);
            }
        } else {
            List<T> tList = new ArrayList<>();
            tList.add(t);
            openNewList(0, tList);
        }
    }

    @Override
    public void addMusicListToNext(List<T> tList) {
        if (data.checkPlayStatus()) {
            List<T> newList = new ArrayList<>();
            for (T t : tList) {
                boolean isSame = false;
                for (T t1 : data.getPlayList()) {
                    if (t.equals(t1)) {
                        isSame = true;
                        break;
                    }
                }
                if(!isSame){
                    newList.add(t);
                }
            }
            if(newList.size() > 0){
                data.setAddMusic(newList.get(0),data.getPosition() + 1);
                data.getPlayList().addAll(data.getPosition() + 1,newList);
            }
        } else {
            openNewList(0, tList);
        }
    }

    @Override
    public void start() {
        if (mediaPlayer == null) return;
        if (!data.checkPlayStatus()) {
            if (mediaPlayerListenerList != null) {
                for (MediaPlayerListener<T> mediaPlayerListener : mediaPlayerListenerList) {
                    mediaPlayerListener.playerNull();
                }
            }
            return;
        }
        mediaPlayer.start();
        T t = data.getPlayList().get(data.getPosition());
        if (mediaPlayerListenerList != null) {
            for (MediaPlayerListener<T> mediaPlayerListener : mediaPlayerListenerList) {
                PlayStatus<T> playStatus = new PlayStatus<>();
                playStatus.setPlaying(true);
                playStatus.setPlayList(data.getPlayList());
                playStatus.setPosition(data.getPosition());
                playStatus.setDuration(mediaPlayer.getDuration());
                playStatus.setCurrentDuration(mediaPlayer.getCurrentPosition());
                mediaPlayerListener.playedMusic(playStatus);
                mediaPlayerListener.startAndPause(true);
            }
        }
    }

    @Override
    public void pause() {
        if (mediaPlayer == null) return;
        if (!data.checkPlayStatus()) {
            if (mediaPlayerListenerList != null) {
                for (MediaPlayerListener<T> mediaPlayerListener : mediaPlayerListenerList) {
                    mediaPlayerListener.playerNull();
                }
            }
            return;
        }
        mediaPlayer.pause();
        if (mediaPlayerListenerList != null) {
            for (MediaPlayerListener<T> mediaPlayerListener : mediaPlayerListenerList) {
                mediaPlayerListener.startAndPause(false);
            }
        }
    }

    @Override
    public void stop() {
        if (mediaPlayer == null) return;
        mediaPlayer.stop();
    }

    @Override
    public void clear() {
        //清除播放
        data.clear();
        stop();
        if (mediaPlayerListenerList != null) {
            for (MediaPlayerListener<T> mediaPlayerListener : mediaPlayerListenerList) {
                mediaPlayerListener.clear();
            }
        }
    }

    @Override
    public void seekTo(int time) {
        if (mediaPlayer == null) return;
        if (!data.checkPlayStatus()) {
            if (mediaPlayerListenerList != null) {
                for (MediaPlayerListener<T> mediaPlayerListener : mediaPlayerListenerList) {
                    mediaPlayerListener.playerNull();
                }
            }
            return;
        }
        mediaPlayer.seekTo(time * 1000);
    }

    @Override
    public void openNewList(int position, List<T> tList) {
        data.setPlayList(tList);
        data.newPlayList();
        data.setRecordPosition(0);
        data.getRecordList().add(position);
        open(data.getRecordList().get(data.getRecordPosition()));
    }

    @Override
    public void open(int position) {
        data.setPosition(position);
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        } else {
            mediaPlayer.reset();
        }
        try {
            mediaPlayer.setDataSource(getPath(data.getPlayList().get(position)));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            next();
        }
    }

    @Override
    public void adjustPlayMode() {
        BasePlayMode basePlayMode = data.getPlayMode();
        for (int i = 0; i < data.getBasePlayModeList().size(); i++) {
            if (basePlayMode == data.getBasePlayModeList().get(i)) {
                int j = i + 1;
                if (j == data.getBasePlayModeList().size()) {
                    j = 0;
                }
                data.setPlayMode(data.getBasePlayModeList().get(j));
                break;
            }
        }
        if (mediaPlayerListenerList != null) {
            for (MediaPlayerListener<T> mediaPlayerListener : mediaPlayerListenerList) {
                mediaPlayerListener.playModeChange(data.getPlayMode());
            }
        }
    }

    @Override
    public PlayStatus<T> getPlayStatus() {
        PlayStatus<T> playStatus = new PlayStatus<>();
        if (data.checkPlayStatus()) {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                playStatus.setPlaying(true);
            } else {
                playStatus.setPlaying(false);
            }
            playStatus.setPlayList(data.getPlayList());
            playStatus.setPosition(data.getPosition());
            playStatus.setCurrentDuration(mediaPlayer.getCurrentPosition());
            playStatus.setDuration(mediaPlayer.getDuration());
        } else {
            playStatus.setPlaying(false);
            playStatus.setPosition(-1);
            playStatus.setPlayList(null);
        }
        return playStatus;
    }

    public interface MediaPlayerListener<T> {
        /**
         * 切换歌曲时回调
         *
         * @param playStatus
         */
        void playedMusic(PlayStatus<T> playStatus);

        /**
         * 清空列表时回调
         */
        void clear();

        /**
         * 暂停/开始播放时回调
         *
         * @param isPlaying true:播放状态
         */
        void startAndPause(boolean isPlaying);

        /**
         * 访问某个操作时播放列表为null时回调
         * 注意:每次出发播放器动作时，播放器如果为null则会回调
         */
        void playerNull();

        /**
         * 当播放模式发生改变时回调
         *
         * @param basePlayMode
         */
        void playModeChange(BasePlayMode basePlayMode);
    }
}
