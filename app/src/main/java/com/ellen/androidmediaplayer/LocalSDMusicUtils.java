package com.ellen.androidmediaplayer;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocalSDMusicUtils {

    private static List<Music> musicList;

    /**
     * 获取本地所有歌曲
     *
     * @param context
     * @return
     */
    public static List<Music> getLocalAllMusic(Context context) {
        if (musicList == null) {
            musicList = new ArrayList<>();
        } else {
            return musicList;
        }

        ContentProviderUtils.getMusicPathList(context, new ContentProviderUtils.IntoMusic() {
            @Override
            public void getMusic(String path, String name, String album, String artist, String type, long size, int duration, int musicId, int albumId) {
                Music music = new Music();
                music.setPath(path);
                music.setName(name);
                music.setAlbum(album);
                music.setArtist(artist);
                music.setSize(size);
                music.setType(type);
                music.setDuration(duration);
                music.setMusicId(musicId);
                music.setAlbumId(albumId);
                musicList.add(music);
            }
        });

        return musicList;
    }
}
