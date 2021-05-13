package com.example.softengine.adapter;

import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.softengine.R;
import com.example.softengine.widget.BaseRecViewHolder;
import com.example.softengine.widget.MyVideoPlayer;

import java.util.List;

class ListVideoAdapter extends BaseRecAdapter<String, VideoViewHolder> {


    public ListVideoAdapter(List<String> list) {
        super(list);
    }

    @Override
    public void onHolder(VideoViewHolder holder, String bean, int position) {
        holder.mp_video.setUp(bean, JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
        if (position == 0) {
            holder.mp_video.startVideo();
        }
        Glide.with(context).load(bean).into(holder.mp_video.thumbImageView);
        holder.tv_title.setText("第" + position + "个视频");
    }

    @Override
    public VideoViewHolder onCreateHolder() {
        return new VideoViewHolder(getViewByRes(R.layout.item_video));

    }


}

public class VideoViewHolder extends BaseRecViewHolder {
    public View rootView;
    public MyVideoPlayer mp_video;
    public TextView tv_title;

    public VideoViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        this.mp_video = rootView.findViewById(R.id.mp_video);
        this.tv_title = rootView.findViewById(R.id.tv_title);
    }

}
