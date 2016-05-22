package com.blue339.lolive.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blue339.lolive.R;

/**
 * Created by zeminwang on 16/5/20.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{


    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_item_news, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 31;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}
