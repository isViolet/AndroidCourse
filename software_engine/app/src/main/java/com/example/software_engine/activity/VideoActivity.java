package com.example.software_engine.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.software_engine.R;
import com.example.software_engine.adapter.BaseRecAdapter;
import com.example.software_engine.mysql.MysqlHelper;
import com.example.software_engine.mysql.UserVideoHelper;
import com.example.software_engine.weight.BaseRecViewHolder;
import com.example.software_engine.weight.MyVideoPlayer;
import com.example.software_engine.utils.SelectMediaUtil;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends Activity {

    RecyclerView rvPage2;
    private ListVideoAdapter videoAdapter;
    private PagerSnapHelper snapHelper;
    private LinearLayoutManager layoutManager;
    private int currentPosition;
    private UserVideoHelper userVideoHelper;
    private MysqlHelper mysqlhelper;
    private SelectMediaUtil selectMediaUtil;
    private List<String> webLists;
    private ArrayList<String> webLists_;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        TextView textView = findViewById(R.id.title);
        textView.setText("我的视频");
        TextView textView1 = findViewById(R.id.tip);
        MysqlHelper mysqlHelper = new MysqlHelper();
        UserVideoHelper userVideoHelper = new UserVideoHelper(this, MainActivity.USER_NAME + "video");
        webLists = mysqlHelper.getWebUrlList(userVideoHelper,MainActivity.USER_NAME + "video");
        webLists_ = new ArrayList<>();
        if (webLists.size() != 0){
            textView1.setVisibility(View.GONE);
            initView();
            addListener();
        }
    }

    private void initView() {

        Log.i("dcas",""+ Environment.getExternalStorageDirectory().getPath());

        rvPage2 = findViewById(R.id.videoList);

        for (int i = webLists.size()-1; i >= 0; i--){
            webLists_.add(webLists.get(i));
        }

        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvPage2);

        videoAdapter = new ListVideoAdapter(webLists_);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPage2.setLayoutManager(layoutManager);
        rvPage2.setAdapter(videoAdapter);

    }

    private void addListener() {

        rvPage2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {}

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        View view = snapHelper.findSnapView(layoutManager);

                        //当前固定后的item position
                        int position = recyclerView.getChildAdapterPosition(view);
                        if (currentPosition != position) {
                            //如果当前position 和 上一次固定后的position 相同, 说明是同一个, 只不过滑动了一点点, 然后又释放了
                            MyVideoPlayer.releaseAllVideos();
                            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                            if (viewHolder != null && viewHolder instanceof VideoViewHolder) {
//                                ((VideoViewHolder) viewHolder).mp_video.startVideo();
                                ((VideoViewHolder) viewHolder).mp_video.startVideo();

                            }
                        }
                        Log.i("dcas",""+newState + "**" +position + "//" +currentPosition);
                        currentPosition = position;
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        break;
                }

            }
        });
    }

    class ListVideoAdapter extends BaseRecAdapter<String, VideoViewHolder> {

        ListVideoAdapter(List<String> list) {
            super(list);
        }

        @Override
        public void onHolder(VideoViewHolder holder, String bean, int position) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

//            setStarView(holder.iv_star,position);
            holder.mp_video.setUp(bean, "第" + position + "个视频", MyVideoPlayer.STATE_NORMAL);
            holder.mp_video.startVideo();
            Glide.with(context).load(bean).into(holder.mp_video.thumbImageView);
        }

        @Override
        public VideoViewHolder onCreateHolder() {
            return new VideoViewHolder(getViewByRes(R.layout.item_page2));
        }

    }

    static class VideoViewHolder extends BaseRecViewHolder {
        View rootView;
        MyVideoPlayer mp_video;
        //        TextView tv_title;
        ImageView iv_star;

        VideoViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mp_video = rootView.findViewById(R.id.mp_video);
//            this.tv_title = rootView.findViewById(R.id.tv_title);
            this.iv_star = rootView.findViewById(R.id.iv_star);
        }
    }

    private void setStarView(ImageView iv_star, int pos){
        mysqlhelper = new MysqlHelper();
        userVideoHelper = new UserVideoHelper(getApplicationContext(),MainActivity.USER_NAME);

        if (mysqlhelper.isExistName(userVideoHelper,MainActivity.USER_NAME,"userVideoUrl",webLists_.get(pos))){
            iv_star.setBackgroundResource(R.drawable.star_on);
        }else {
            iv_star.setBackgroundResource(R.drawable.star_off);
        }

        iv_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mysqlhelper.isExistName(userVideoHelper,MainActivity.USER_NAME,"userVideoUrl",webLists_.get(pos))){
                    iv_star.setBackgroundResource(R.drawable.star_off);
                    boolean fla = mysqlhelper.deleteWebPath(userVideoHelper,MainActivity.USER_NAME,webLists_.get(pos));
                    if (fla){
                        Toast.makeText(getApplication(),"删除成功"+pos,Toast.LENGTH_SHORT).show();
                    }
                }else {
                    iv_star.setBackgroundResource(R.drawable.star_on);
                    boolean fla = mysqlhelper.insertWebPath(userVideoHelper,MainActivity.USER_NAME,webLists_.get(pos));
                    if (fla){
                        Toast.makeText(getApplication(),"添加数据库成功"+pos,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
