package com.ellen.mediaplaylibrary;

import com.ellen.mediaplaylibrary.playmode.BasePlayMode;

import java.util.List;

/**
 * 播放器的所有数据
 * 0.播放模式集合
 * 1.播放模式
 * 2.播放列表
 * 3.播放历史
 * 4.播放位置
 * 5.是否为播放历史播放模式
 */
public class Data<T> {

    private List<BasePlayMode> basePlayModeList;
    private BasePlayMode playMode;
    private List<T> playList;
    private int position = -1;
    private RecordData<T> recordData;

    Data(){
        recordData = new RecordData<>();
    }

    void clear(){
        playList = null;
        position = -1;
        recordData = new RecordData<>();
    }

    void newPlayList(){
        recordData = new RecordData<>();
    }

    public BasePlayMode getPlayMode() {
        return playMode;
    }

    /**
     *
     * @return true:可以播放，false:不能播放
     */
    boolean checkPlayStatus() {
        return position >= 0 && playList != null && playList.size() > 0;
    }

    void setPlayMode(BasePlayMode playMode) {
        this.playMode = playMode;
    }

    public List<T> getPlayList() {
        return playList;
    }

    void setPlayList(List<T> playList) {
        this.playList = playList;
    }

    List<Integer> getRecordList() {
        return recordData.getRecordList();
    }

    public int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }

    List<BasePlayMode> getBasePlayModeList() {
        return basePlayModeList;
    }

    void setBasePlayModeList(List<BasePlayMode> basePlayModeList) {
        this.basePlayModeList = basePlayModeList;
    }
    int getRecordPosition() {
        return recordData.getRecordPosition();
    }

    void setRecordPosition(int recordPosition) {
        recordData.setRecordPosition(recordPosition);
    }
}
