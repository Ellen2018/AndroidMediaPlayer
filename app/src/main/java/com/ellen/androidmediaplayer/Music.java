package com.ellen.androidmediaplayer;

import java.io.File;
import java.io.Serializable;

public class Music {

    /**
     * 1->按照歌手进行分类
     * 2->按照专辑分类
     * 3->按照流派分类
     */
    private static int biJiao = 1;
    /**
     * 1.比较歌名
     */
    private static int sortTag = 1;

    public static int getBiJiao() {
        return biJiao;
    }

    public static void setBiJiao(int biJiao) {
        Music.biJiao = biJiao;
    }

    /**
     * 歌曲本地路径
     */
    private String path;
    /**
     * 歌曲名
     */
    private String name;
    /**
     * 歌曲专辑
     */
    private String album;
    /**
     * 歌手名
     */
    private String artist;
    /**
     * 歌曲文件大小
     */
    private long size;
    /**
     * 歌曲时长
     */
    private int duration;
    /**
     * 歌曲id
     */
    private int musicId;
    /**
     * 专辑id
     */
    private int albumId;
    /**
     * 父目录
     */
    private String fatherPath;
    /**
     * 类别:流派
     */
    private String type;
    private String pyName;
    private String pySingerName;
    private String pyAlbumName;

    public String getPyName() {
        return pyName;
    }

    public void setPyName(String pyName) {
        this.pyName = pyName;
    }

    public String getPySingerName() {
        return pySingerName;
    }

    public void setPySingerName(String pySingerName) {
        this.pySingerName = pySingerName;
    }

    public String getPyAlbumName() {
        return pyAlbumName;
    }

    public void setPyAlbumName(String pyAlbumName) {
        this.pyAlbumName = pyAlbumName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        fatherPath = new File(path).getParentFile().getAbsolutePath();
    }

    public String getFatherPath() {
        return fatherPath;
    }

    public void setFatherPath(String fatherPath) {
        this.fatherPath = fatherPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    /**
     * 歌曲的唯一标识
     *
     * @return
     */
    public String getWeiOneTag() {
        return this.getMusicId() + "_" + this.getAlbumId();
    }

}
