package com.blue339.lolive.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blue339.lolive.R;

/**
 * Created by zeminwang on 16/5/20.
 */
public class TvLiveAdapter extends RecyclerView.Adapter<TvLiveAdapter.ViewHolder>{


    @Override
    public TvLiveAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item_tvlive, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TvLiveAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 100;
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
