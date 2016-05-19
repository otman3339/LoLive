package com.blue339.lolive.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blue339.lolive.R;

/**
 * Created by zeminwang on 16/5/19.
 */
public class AdviceChannelAdapter extends RecyclerView.Adapter<AdviceChannelAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_item_navigation, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAvatar;
        TextView tvTitle;

        public ViewHolder(View view) {
            super(view);
            ivAvatar = (ImageView) view.findViewById(R.id.iv_avatar);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
        }
    }
}
