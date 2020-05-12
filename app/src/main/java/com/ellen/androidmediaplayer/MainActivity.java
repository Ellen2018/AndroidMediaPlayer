package com.ellen.androidmediaplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ellen.androidmediaplayer.base.BaseActivity;
import com.ellen.androidmediaplayer.base.adapter.recyclerview.BaseRecyclerViewAdapter;
import com.ellen.androidmediaplayer.base.adapter.recyclerview.BaseViewHolder;
import com.ellen.mediaplaylibrary.EllenMediaPlayer;
import com.ellen.mediaplaylibrary.PlayStatus;
import com.ellen.mediaplaylibrary.playmode.BasePlayMode;
import com.ellen.mediaplaylibrary.playmode.SuiJiPlayMode;
import com.ellen.mediaplaylibrary.playmode.XunHuanPlayMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private PermissionUtils permissionUtils;
    private MyMediaPlayer myMediaPlayer;
    private TextView tvMusicName,tvPre,tvPauseStart,tvNext;

    @Override
    protected void setStatus() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        tvMusicName = findViewById(R.id.tv_music_name);
        tvPre = findViewById(R.id.tv_pre);
        tvPauseStart = findViewById(R.id.tv_pause_start);
        tvNext = findViewById(R.id.tv_next);
        tvPre.setOnClickListener(this);
        tvPauseStart.setOnClickListener(this);
        tvNext.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        List<BasePlayMode> basePlayModes = new ArrayList<>();
        basePlayModes.add(new SuiJiPlayMode());
        myMediaPlayer = new MyMediaPlayer(basePlayModes,0);
        myMediaPlayer.addPlayedListener(new EllenMediaPlayer.MediaPlayerListener<Music>() {
            @Override
            public void playedMusic(PlayStatus<Music> playStatus) {
                if(!playStatus.isPlayerNull()) {
                    Music music = playStatus.getPlayList().get(playStatus.getPosition());
                    tvMusicName.setText(music.getName());
                }
            }

            @Override
            public void clear() {
               Log.e("Ellen2018","停止了播放");
            }

            @Override
            public void startAndPause(boolean isPlaying) {
                if(isPlaying) {
                    Log.e("Ellen2018", "播放中......");
                }else {
                    Log.e("Ellen2018", "没有播放");
                }
            }

            @Override
            public void playerNull() {
                Log.e("Ellen2018", "播放列表为null");
            }

            @Override
            public void playModeChange(BasePlayMode basePlayMode) {

            }
        });
       permissionUtils = new PermissionUtils(this);
       permissionUtils.startCheckFileReadWritePermission(0, new PermissionUtils.PermissionCallback() {
           @Override
           public void success() {
               final List<Music> musicList = LocalSDMusicUtils.getLocalAllMusic(MainActivity.this);
               recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
               MusicAdapter musicAdapter = new MusicAdapter(MainActivity.this,musicList);
               recyclerView.setAdapter(musicAdapter);
               musicAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
                   @Override
                   public void onItemClick(BaseViewHolder baseViewHolder, int position) {
                       myMediaPlayer.openNewList(position,musicList);
                   }
               });
           }

           @Override
           public void failure() {

           }
       });
    }

    @Override
    protected void destory() {

    }

    @Override
    protected Boolean isSetVerticalScreen() {
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_pre:
                myMediaPlayer.preByUser();
                break;
            case R.id.tv_next:
                myMediaPlayer.nextByUser();
                break;
            case R.id.tv_pause_start:
                myMediaPlayer.startOrPause();
                break;
        }
    }
}
