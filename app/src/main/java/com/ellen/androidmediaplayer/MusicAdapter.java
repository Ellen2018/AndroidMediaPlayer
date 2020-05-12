package com.ellen.androidmediaplayer;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ellen.androidmediaplayer.base.adapter.recyclerview.BaseSingleRecyclerViewAdapter;
import com.ellen.androidmediaplayer.base.adapter.recyclerview.BaseViewHolder;

import java.util.List;

public class MusicAdapter extends BaseSingleRecyclerViewAdapter<Music, MusicAdapter.MusicViewHolder> {


    public MusicAdapter(Context context, List<Music> dataList) {
        super(context, dataList);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_music;
    }

    @Override
    protected MusicViewHolder getNewViewHolder(View view) {
        return new MusicViewHolder(view);
    }

    @Override
    protected void showData(MusicViewHolder musicViewHolder, Music data, int position) {
         musicViewHolder.tvMusicName.setText(data.getName());
    }

    static class MusicViewHolder extends BaseViewHolder{
        TextView tvMusicName;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMusicName = findViewById(R.id.tv_music_name);
        }
    }

}
