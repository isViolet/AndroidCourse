package com.example.software_engine.fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.software_engine.R;
import com.example.software_engine.activity.MainActivity;
import com.example.software_engine.adapter.BaseRecAdapter;
import com.example.software_engine.mysql.MysqlHelper;
import com.example.software_engine.mysql.UserStarHelper;
import com.example.software_engine.weight.BaseRecViewHolder;
import com.example.software_engine.weight.MyVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class IndexFragment extends Fragment{

    RecyclerView rvPage2;
    private List<String> urlList;
    private ListVideoAdapter videoAdapter;
    private PagerSnapHelper snapHelper;
    private LinearLayoutManager layoutManager;
    private int currentPosition;
    private ImageView starView;
    private UserStarHelper userStarHelper;
    private MysqlHelper mysqlhelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_index, container, false);
        rvPage2 = view.findViewById(R.id.rv_page2);
        TextView textView = view.findViewById(R.id.title);
        textView.setText("首页");
        initView();
        addListener();
        return view;

    }

    private void initView() {

        urlList = new ArrayList<>();
        urlList.add("http://isviolet.com/wp-content/uploads/2020/05/IMG_1170.mp4");
        urlList.add("https://qiniu-xpc13.xpccdn.com/5eb5055256e08.mp4");
        urlList.add("https://qiniu-xpc4.xpccdn.com/5ebcc24237f58.mp4");
        urlList.add("https://qiniu-xpc4.xpccdn.com/5eb66c63e4ef1.mp4");
        urlList.add("https://qiniu-xpc4.xpccdn.com/5eb93ef71cab9.mp4");

        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rvPage2);

        videoAdapter = new ListVideoAdapter(urlList);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
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

        if (mysqlhelper.isExistName(userStarHelper,MainActivity.USER_NAME,"webUrl",urlList.get(pos))){
            iv_star.setBackgroundResource(R.drawable.star_on);
        }else {
            iv_star.setBackgroundResource(R.drawable.star_off);
        }

        iv_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mysqlhelper.isExistName(userStarHelper,MainActivity.USER_NAME,"webUrl",urlList.get(pos))){
                    iv_star.setBackgroundResource(R.drawable.star_off);
                    boolean fla = mysqlhelper.deleteWebPath(userStarHelper,MainActivity.USER_NAME,urlList.get(pos));
                    if (fla){
                        Toast.makeText(getContext(),"删除成功"+pos,Toast.LENGTH_SHORT).show();
                    }
                }else {
                    iv_star.setBackgroundResource(R.drawable.star_on);
                    boolean fla = mysqlhelper.insertWebPath(userStarHelper,MainActivity.USER_NAME,urlList.get(pos));
                    if (fla){
                        Toast.makeText(getContext(),"添加数据库成功"+pos,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}
