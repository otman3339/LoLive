package com.blue339.lolive.adapter;

import android.content.Intent;
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
import com.blue339.lolive.activity.PlayerActivity;

/**
 * Created by zeminwang on 16/5/20.
 */
public class TvLiveAdapter extends RecyclerView.Adapter<TvLiveAdapter.ViewHolder>{


    @Override
    public TvLiveAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item_tvlive, parent, false);

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

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(), PlayerActivity.class);
//                intent.putExtra("swfUrl", "http://weblbs.yystatic.com/s/92174038/2503273946/huyacoop.swf");
                intent.putExtra("swfUrl", "http://v.behe.com/2016/05/19/c558ecbd6007f0e989a9f73bb4fb1fde.swf");
                parent.getContext().startActivity(intent);
            }
        });
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TvLiveAdapter.ViewHolder holder, int position) {

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

            tvLiveImage = (ImageView) itemView.findViewById(R.id.tvlive_image);
            tvLiveTitle = (TextView) itemView.findViewById(R.id.tvlive_title);
        }
    }
}
