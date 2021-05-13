package com.example.software_engine.fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.software_engine.R;
import com.example.software_engine.activity.LoginInterfaceActivity;
import com.example.software_engine.activity.MainActivity;
import com.example.software_engine.adapter.BaseRecAdapter;
import com.example.software_engine.mysql.MysqlHelper;
import com.example.software_engine.mysql.UserStarHelper;
import com.example.software_engine.weight.BaseRecViewHolder;
import com.example.software_engine.weight.MyVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class StarFragment extends Fragment{

    RecyclerView rvPage2;
    private List<String> urlList;
    private ListVideoAdapter videoAdapter;
    private PagerSnapHelper snapHelper;
    private LinearLayoutManager layoutManager;
    private int currentPosition;
    private ImageView starView;
    private UserStarHelper userStarHelper;
    private MysqlHelper mysqlhelper;
    private List<String> webLists;
    private List<String> webLists_;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_star, container, false);
        rvPage2 = view.findViewById(R.id.starList);
        TextView textView = view.findViewById(R.id.title);
        textView.setText("喜欢");
        MysqlHelper mysqlHelper = new MysqlHelper();
        UserStarHelper userStarHelper = new UserStarHelper(getContext(), MainActivity.USER_NAME);
        webLists = mysqlHelper.getWebUrlList(userStarHelper,MainActivity.USER_NAME);
        webLists_ = new ArrayList<>();
        if (webLists.size() != 0){
            initView();
            addListener();
        }
        return view;
    }

    private void initView() {

        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvPage2);

        for (int i = webLists.size()-1; i >= 0; i--){
            webLists_.add(webLists.get(i));
        }
        videoAdapter = new ListVideoAdapter(webLists_);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvPage2.setLayoutManager(layoutManager);
        rvPage2.setAdapter(videoAdapter);

    }

    private void addListener() {

        rvPage2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


            }

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
                                ((VideoViewHolder) viewHolder).mp_video.startVideo();
                            }
                        }
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


    @Override
    public void onPause() {
        super.onPause();
        MyVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onResume() {
        super.onResume();
        MyVideoPlayer.releaseAllVideos();
    }

    class ListVideoAdapter extends BaseRecAdapter<String, VideoViewHolder> {

        ListVideoAdapter(List<String> list) {
            super(list);
        }

        @Override
        public void onHolder(VideoViewHolder holder, String bean, int position) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

            setStarView(holder.iv_star,position);
            holder.mp_video.setUp(bean, "第" + position + "个视频", MyVideoPlayer.STATE_NORMAL);
            holder.mp_video.startVideo();
            Glide.with(context).load(bean).into(holder.mp_video.thumbImageView);
//            holder.tv_title.setText("第" + position + "个视频");

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
        userStarHelper = new UserStarHelper(getContext(),MainActivity.USER_NAME);

        if (mysqlhelper.isExistName(userStarHelper,MainActivity.USER_NAME,"webUrl",webLists.get(pos))){
            iv_star.setBackgroundResource(R.drawable.star_on);
        }else {
            iv_star.setBackgroundResource(R.drawable.star_off);
        }

        iv_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mysqlhelper.isExistName(userStarHelper,MainActivity.USER_NAME,"webUrl",webLists.get(pos))){
                    iv_star.setBackgroundResource(R.drawable.star_off);
                    boolean fla = mysqlhelper.deleteWebPath(userStarHelper,MainActivity.USER_NAME,webLists.get(pos));
                    if (fla){
                        Toast.makeText(getContext(),"删除成功"+pos,Toast.LENGTH_SHORT).show();
                    }
                }else {
                    iv_star.setBackgroundResource(R.drawable.star_on);
                    boolean fla = mysqlhelper.insertWebPath(userStarHelper,MainActivity.USER_NAME,webLists.get(pos));
                    if (fla){
                        Toast.makeText(getContext(),"添加数据库成功"+pos,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
