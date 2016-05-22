package com.blue339.lolive.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.blue339.lolive.R;

/**
 * Created by zeminwang on 16/5/20.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{


    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item_video, parent, false);

        final Animation animation = AnimationUtils.loadAnimation(parent.getContext(), R.anim.fade_zoom_out);

        animation.setFillEnabled(true);
        animation.setFillAfter(true);

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                view.startAnimation(animation);
                return false;
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_CANCEL
                        || motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    view.clearAnimation();
                }
                return false;
            }
        });

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VideoAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 31;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView tvLiveImage;
        TextView tvLiveTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            tvLiveImage = (ImageView) itemView.findViewById(R.id.video_image);
            //tvLiveTitle = (TextView) itemView.findViewById(R.id.tvlive_title);
        }
    }
}
