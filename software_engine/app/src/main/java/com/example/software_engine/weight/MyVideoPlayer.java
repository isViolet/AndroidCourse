package com.example.software_engine.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.danikula.videocache.HttpProxyCacheServer;
import com.example.software_engine.R;

import cn.jzvd.JzvdStd;

public class MyVideoPlayer extends JzvdStd {
    public RelativeLayout rl_touch_help;
    private ImageView iv_start;
    private RelativeLayout rl_start;

    private Context context;


    public MyVideoPlayer(Context context) {
        super(context);
        this.context = context;
    }

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public void onAutoCompletion() {

        thumbImageView.setVisibility(View.GONE);

        if (screen == SCREEN_FULLSCREEN) {
            onStateAutoComplete();
            setUp((String) jzDataSource.getCurrentUrl(), jzDataSource.title, SCREEN_FULLSCREEN);
        } else {
            super.onAutoCompletion();
            setUp((String) jzDataSource.getCurrentUrl(), jzDataSource.title, SCREEN_NORMAL);
        }
        //循环播放
        startVideo();
    }


    @Override
    public void setUp(String url, String title, int screen) {
        super.setUp(url, title, screen);
        if (url.startsWith("http")) {
            HttpProxyCacheServer proxy = MyApp.getProxy(context);
            String proxyUrl = proxy.getProxyUrl(url);
            super.setUp(proxyUrl, title, screen);
        } else {
            super.setUp(url, title, screen);
        }

    }

    @Override
    public void init(final Context context) {
        super.init(context);

        rl_touch_help = findViewById(R.id.rl_touch_help);
        rl_start = findViewById(R.id.ll_start);
        iv_start = findViewById(R.id.iv_start);
        resetPlayView();

        rl_touch_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPlayView();
                if (isPlay()) {
//                    fullscreenButton.performClick();
                    goOnPlayOnPause();
                }else {
                    //暂停
                    if (state == STATE_PAUSE) {
                        goOnPlayOnResume();
                    } else {
                        startVideo();
                    }
                }
                resetPlayView();

            }
        });

        rl_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay()) {
                    goOnPlayOnPause();
                } else {
                    //暂停
                    if (state == STATE_PAUSE) {
                        goOnPlayOnResume();
                    } else {
                        startVideo();
                    }
                }
                resetPlayView();
            }
        });
    }

    @Override
    public void startVideo() {
        if (screen == SCREEN_FULLSCREEN) {
            startFullscreenDirectly(context, MyVideoPlayer.class, jzDataSource);
            onStatePreparing();
            rl_start.setVisibility(VISIBLE);
        } else {
            super.startVideo();
            rl_start.setVisibility(GONE);
        }
        resetPlayView();
    }


    private void resetPlayView() {
        if (isPlay()) {
            rl_start.setVisibility(GONE);
            iv_start.setBackgroundResource(R.mipmap.video_play_parse);
        } else {
            rl_start.setVisibility(VISIBLE);
            iv_start.setBackgroundResource(R.mipmap.stop);
        }
    }

    private boolean isPlay() {
        return state == STATE_PREPARING || state == STATE_PLAYING || state == -1;
    }

}
