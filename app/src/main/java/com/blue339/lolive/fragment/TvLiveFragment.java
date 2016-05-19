package com.blue339.lolive.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blue339.lolive.R;

/**
 * Created by Blue on 2016/5/19.
 */
public class TvLiveFragment extends Fragment {

    private int index;

    public TvLiveFragment(){
        super();
    }

    public TvLiveFragment(int i) {
        super();
        this.index = i;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_live, container, false);
        TextView textView = (TextView) view.findViewById(R.id.content);
        textView.setText("Fragment(" + (index + 1) + ")");
        return view;
    }


}
